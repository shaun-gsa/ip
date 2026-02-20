package Task;

public class Task {
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return "T | " + status + " | " + description;
    }
}
