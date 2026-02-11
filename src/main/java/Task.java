public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void markDone() {
        isDone = true;
    }

    void unmarkDone() {
        isDone = false;
    }

    String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getStatus() + " " + description;
    }
}
