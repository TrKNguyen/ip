public class Task {
    protected String desciption;
    protected boolean isDone;
    public Task(String desciption) {
        this.desciption = desciption;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void mark() {
        isDone = true;
    }

    public void unMark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + desciption;
    }
}
