package chatbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the chatbot.
 * A Deadline has a description, a completion status, and a due date.
 */

public class Deadline extends Task {
    private LocalDate deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(boolean isDone, String description, String deadline) {
        this.description = description;
        this.isDone = isDone;
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public void snooze() {
        deadline = deadline.plusDays(1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(formatter) + ")";

    }

    @Override
    public String toFile() {
        return "chatbot.task.ToDo||" + (isDone ? "X" : "") + "||" + description + "||" + deadline;
    }
}
