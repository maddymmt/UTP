package zad1;

import java.util.concurrent.Callable;

class TaskManager implements Callable<State> {

    private final int buttonId;
    private boolean isAwaiting = false;

    public TaskManager(int buttonId) {
        this.buttonId = buttonId;
    }

    public void setAwaiting(boolean awaiting) {
        isAwaiting = awaiting;
    }

    @Override
    public State call() throws Exception {
        int sum = 0;
        int limit = buttonId * 1000;
        while (sum < limit) {
            synchronized (this) {
                while (isAwaiting) {
                    wait();
                }
            }
            int random = (int) Math.floor(Math.random() * 100 + 1);
            sum += random;
            Window.getTextArea().append("Thread " + buttonId + " (limit = " + limit + "): " + random + ", sum = " + sum + "\n");
            Thread.sleep((int) ((Math.floor(Math.random() * 3)) * 1000));
        }

        return State.DONE;
    }
}
