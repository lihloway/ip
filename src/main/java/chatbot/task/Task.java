package chatbot.task;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final Map<String, Function<String[], Task>> registeredTasks = new HashMap<>(){{
        put("chatbot.task.ToDo", parsed -> new ToDo(parsed[1].equals("X"), parsed[2]));
        put("chatbot.task.Deadline", parsed -> new Deadline(parsed[1].equals("X"), parsed[2], parsed[3]));
        put("chatbot.task.Event", parsed -> new Event(parsed[1].equals("X"), parsed[2], parsed[3], parsed[4]));
    }};

    public void complete(){
        isDone = true;
    }

    public void uncomplete(){
        isDone = false;
    }

    public void change_completion(){
        isDone = !isDone;
    }

    public boolean isDone(){
        return this.isDone;
    }

    public static Task fromFile(String line){
        String[] parsed = line.split("\\|\\|");
        return registeredTasks.get(parsed[0]).apply(parsed);
    }
    @Override
    public String toString(){
        if (isDone){
            return "[X] "+ description;
        }
        return "[ ] "+ description;
    }

    public abstract String toFile();
}
