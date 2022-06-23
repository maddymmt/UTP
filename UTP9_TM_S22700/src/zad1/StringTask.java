package zad1;

public class StringTask implements Runnable {

    private String word;
    private int number;
    private String result = "";
    volatile TaskState state = TaskState.CREATED;
    Thread thread = new Thread(this);

    public StringTask(String word, int number) {
        this.word = word;
        this.number = number;
    }

    public TaskState getState() {
        return state;
    }

    public void start() {
        thread.start();
        state = TaskState.RUNNING;
    }

    public void abort() {
        state = TaskState.ABORTED;
        thread.interrupt();
    }

    public boolean isDone() {
        return (state == TaskState.READY || state == TaskState.ABORTED);
    }

    public String getResult() {
        return result;
    }

    @Override
    public void run() {
        for (int i = 0; i < number; i++) {
            if(thread.isInterrupted()){
                return;
            }
            result += word;
        }
        state = TaskState.READY;
    }
}
