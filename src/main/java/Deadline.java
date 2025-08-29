import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate deadline;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public Deadline(String description, String deadline){
        this.description = description;
        this.isDone = false;
        this.deadline = LocalDate.parse(deadline);
    }
    public Deadline(boolean isDone, String description, String deadline){
        this.description = description;
        this.isDone = isDone;
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + deadline.format(formatter) + ")";

    }

    @Override
    public String toFile(){
        return "ToDo||" + (isDone ? "X":"") + "||" + description + "||" + deadline;
    }
}
