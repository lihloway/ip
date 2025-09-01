package chatbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate start;
    LocalDate end;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public Event(String description, String start, String end){
        this.description = description;
        this.isDone = false;
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }
    public Event(boolean isDone, String description, String start, String end){
        this.description = description;
        this.isDone = isDone;
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }

    @Override
    public String toFile(){
        return "chatbot.task.ToDo||" + (isDone ? "X":"") + "||" + description + "||" + start + "||" + end;
    }
}
