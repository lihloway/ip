package chatbot;

import chatbot.io.TaskList;
import chatbot.command.Parser;
import chatbot.command.Response;
import chatbot.command.Ui;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

public class Harry {
    protected TaskList data;
    protected Ui userInput;
    protected Parser parser;
    boolean exit = false;
    private final Map<String, Consumer<String[]>> commands = Map.of(
            "mark", this::command_mark,
            "unmark", this::command_unmark,
            "delete", this::command_delete,
            "list", this::command_list,
            "todo", this::command_todo,
            "deadline", this::command_deadline,
            "event", this::command_event,
            "bye", this::command_bye,
            "find", this::command_find,
            "", this::command_none
    );

    public static void main(String[] args) {
        Harry chatbot = new Harry();
        chatbot.begin_interact();
    }

    Harry() {
        data = new TaskList();
        userInput = new Ui(System.in);
        parser = new Parser(commands);
    }

    protected void begin_interact() {
        String input = "";
        exit = false;
        say_hello();

        while(!exit){
            input = userInput.readNext();
            String[] parts = input.split(" ",2);
            parser.getCommand(parts[0]).accept(parts);
        }
        say_goodbye();
    }

    private void say_hello() {
        print_line();
        System.out.println("Hello from\n" + get_name());
        System.out.println("Is there anything I can do for you?");
        print_line();
    }
    private void say_goodbye() {
        print_line();
        System.out.println("Nice talking to you! I hope to see you again.");
        print_line();
    }

    private void command_mark(String[] input){
        try {
            int item = Integer.parseInt(input[1]) - 1;
            data.complete(item);
            print_line();
            System.out.println(Response.MARK_SUCCESS.getMessage() + data.print(item));
        } catch (NumberFormatException e) {
            System.out.println(Response.NUMBER_FAILURE.getMessage());
        }
        catch (Exception e) {
            System.out.println(Response.MARK_FAILURE.getMessage());
        }
        finally{
            print_line();
        }
    }

    private void command_unmark(String[] input){
        try {
            int item = Integer.parseInt(input[1]) - 1;
            data.uncomplete(item);
            print_line();
            System.out.println(Response.UNMARK_SUCCESS.getMessage() + data.print(item));
        } catch (NumberFormatException e) {
            System.out.println(Response.NUMBER_FAILURE.getMessage());
        }
        catch (Exception e) {
            System.out.println(Response.UNMARK_FAILURE.getMessage());
        }
        finally{
            print_line();
        }
    }

    private void command_delete(String[] input){
        try {
            int item = Integer.parseInt(input[1]) - 1;
            print_line();
            System.out.println(Response.REMOVE_TASK.getMessage() + data.print(item));
            data.remove(item);
            System.out.println("You have " + data.getSize() + " tasks remaining.");
        } catch (NumberFormatException e) {
            System.out.println(Response.NUMBER_FAILURE.getMessage());
        }
        catch (Exception e) {
            System.out.println(Response.DELETE_FAILURE.getMessage());
        }
        finally{
            print_line();
        }
    }

    private void command_list(String[] input){
        print_line();
        System.out.println(Response.LIST_TASKS.getMessage());
        for (int index = 1; index < data.getSize() + 1; index++){
            System.out.println(index + ". " + data.print(index - 1));
        }
        if (data.getSize() == 0){
            System.out.println(Response.LIST_FAILURE.getMessage());
        }
        print_line();
    }

    private void command_todo(String[] input){
        try{
            data.add(new ToDo(input[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("HEY! What are we chatbot.task.ToDo??");
            print_line();
            return;
        }
        print_line();
        System.out.println(Response.TASK_SUCCESS.getMessage());
        System.out.println(data.printLast());
        System.out.println("Now you have " + data.getSize() + " tasks in the list.");
        print_line();
    }

    private void command_deadline(String[] input){
        try{
            String[] arguments = input[1].split(" /by ");
            data.add(new Deadline(arguments[0],arguments[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("HEY! What deadline??");
            print_line();
            return;
        }
        print_line();
        System.out.println(Response.TASK_SUCCESS.getMessage());
        System.out.println(data.printLast());
        System.out.println("Now you have " + data.getSize() + " tasks in the list.");
        print_line();

    }

    private void command_event(String[] input){
        try{
            String[] arguments = input[1].split(" /(from |to )");
            data.add(new Event(arguments[0],arguments[1],arguments[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("HEY! What event??");
            print_line();
            return;
        }
        print_line();
        System.out.println(Response.TASK_SUCCESS.getMessage());
        System.out.println(data.printLast());
        System.out.println("Now you have " + data.getSize() + " tasks in the list.");
        print_line();
    }

    private void command_bye(String[] input){
        exit = true;
    }

    private void command_find(String[] input){
        ArrayList<Task> filteredList = data.filter((Task t) -> t.toString().matches(".*"+input[1]+".*"));
        print_line();
        System.out.println(Response.LIST_TASKS.getMessage() + " (filtered)");
        for (int index = 1; index < filteredList.size() + 1; index++){
            System.out.println(index + ". " + filteredList.get(index-1).toString());
        }
        if (filteredList.isEmpty()){
            System.out.println(Response.LIST_FAILURE.getMessage());
        }
        print_line();
    }
    private void command_none(String[] input){
        System.out.println(input[0] + "? I have no clue what you're talking about buddy.");
        System.out.println(">:(");
        print_line();
    }

    private void print_line() {
        System.out.println("____________________________________________________________");
    }

    private String get_name() {
        return " _    _\n"
                + "| |  | |                  @\n"
                + "| |__| | ____ ____ ____   _\n"
                + "|  __  |/ _  |  __|  __| | |\n"
                + "| |  | | (_| | |  | |    | |\n"
                + "|_|  |_|\\__/_|_|  |_|    |_|\n";
    }
}