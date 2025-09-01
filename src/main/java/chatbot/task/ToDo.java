package chatbot.task;

public class ToDo extends Task {
    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }
    public ToDo(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFile() {
        return "chatbot.task.ToDo||" + (isDone ? "X" : "") + "||" + description;
    }
}
