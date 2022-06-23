package zad1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class Button extends JButton {

    String text;
    static State state;
    static ExecutorService executor = Executors.newCachedThreadPool();
    static List<Future<State>> futures = new ArrayList<>();
    static List<TaskManager> managers = new ArrayList<>();

    public Button(String text) {
        super(text);
        setState(State.RUNNING);
        addActionListener(e -> {
            try {
                handleButton(this, e);
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    public static void setState(State state) {
        Button.state = state;
    }

    static void handleButton(Button button, ActionEvent e) throws ExecutionException {
        int buttonId = Integer.parseInt(button.getText().replaceAll("\\D+", ""));
        switch (state) {
            case RUNNING:
                TaskManager taskManager = new TaskManager(buttonId);
                button.setText("Suspend T" + buttonId);
                Future<State> future = executor.submit(taskManager);
                futures.add(future);
                managers.add(taskManager);
                setState(State.SUSPENDED);
                break;
            case SUSPENDED:
                managers.get(buttonId - 1).setAwaiting(true);
                button.setText("Continue T" + buttonId);
                setState(State.CONTINUE);
                break;
            case CONTINUE:
                button.setText("Suspend T" + buttonId);
                synchronized (managers.get(buttonId - 1)) {
                    managers.get(buttonId - 1).setAwaiting(false);
                    managers.get(buttonId - 1).notify();
                }
                setState(State.SUSPENDED);
                break;
            case CANCELLED:
                button.setEnabled(false);
                button.setText("T" + buttonId + " Cancelled");
                Window.getTextArea().append("Thread " + buttonId + ": Cancelled!");
                break;
            case DONE:
                button.setEnabled(false);
                button.setText("T" + buttonId + " Done!");
                Window.getTextArea().append("Thread " + buttonId + ": Done!");
                synchronized (managers.get(buttonId - 1)) {
                    managers.get(buttonId - 1).notify();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    static void cancel(int i) {
        synchronized (futures.get(i)) {
            futures.get(i).cancel(true);
        }
    }
}
