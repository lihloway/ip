public class Deadline extends Task{
    String deadline;
    public Deadline(String description, String deadline){
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
    }
    public Deadline(boolean isDone, String description, String deadline){
        this.description = description;
        this.isDone = isDone;
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String toFile(){
        return "ToDo||" + (isDone ? "X":"") + "||" + description + "||" + deadline;
    }
}
