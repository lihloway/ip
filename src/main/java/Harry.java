import java.util.ArrayList;
import java.util.Scanner;

public class Harry {
    protected Scanner scanner;
    protected ArrayList<Task> data;

    public static void main(String[] args) {
        Harry chatbot = new Harry();
    }

    @SuppressWarnings("unchecked")
    Harry() {
        scanner = new Scanner(System.in);
        data = new ArrayList<>(100);
        begin_interact();
    }

    protected void begin_interact() {
        String input = "";
        boolean exit = false;
        say_hello();

        while(!exit){
            input = scanner.nextLine();
            String[] parts = input.split(" ",2);
            String[] arguments;
            if(data.size() == 100){
                throw new HarryException("I'm Full");
            }
            switch (parts[0]) {
                case "mark":
                    try {
                        int item = Integer.parseInt(parts[1]) - 1;
                        data.get(item).complete();
                        print_line();
                        System.out.println("Nice! I've marked this task as done:\n" + data.get(item).toString());
                    } catch (NumberFormatException e) {
                        System.out.println("I need a number index");
                    }
                    catch (Exception e) {
                        System.out.println("There's nothing to mark!");
                    }
                    finally{
                        print_line();
                    }
                    break;
                case "unmark":
                    try {
                        int item = Integer.parseInt(parts[1]) - 1;
                        print_line();
                        data.get(item).uncomplete();
                        System.out.println("OK, I've marked this task as not done yet:\n" +data.get(item).toString());
                    } catch (NumberFormatException e) {
                        System.out.println("I need a number index");
                    }
                    catch (Exception e) {
                        System.out.println("There's nothing to unmark!");
                    }
                    finally{
                        print_line();
                    }
                    break;
                case "delete":
                    try {
                        int item = Integer.parseInt(parts[1]) - 1;
                        print_line();
                        System.out.println("Noted. I've removed this task:\n" +data.get(item).toString());
                        data.remove(item);
                        System.out.println("You have " + data.size() + " tasks remaining.");
                    } catch (NumberFormatException e) {
                        System.out.println("I need a number index");
                    }
                    catch (Exception e) {
                        System.out.println("There's nothing to delete!");
                    }
                    finally{
                        print_line();
                    }
                    break;
                case "list":
                    print_line();
                    System.out.println("Here are the tasks in your list:");
                    for (int index = 1; index < data.size() + 1; index++){
                        System.out.println(index + ". " + data.get(index -1).toString());
                    }
                    if (data.isEmpty()){
                        System.out.println("There's nothing here!");
                    }
                    print_line();
                    break;
                case "todo":
                    try{
                        data.add(new ToDo(parts[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("HEY! What are we ToDo??");
                        print_line();
                        break;
                    }
                    print_line();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(data.get(data.size()-1).toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    print_line();
                    break;

                case "deadline":
                    try{
                        arguments = parts[1].split(" /by ");
                        data.add(new Deadline(arguments[0],arguments[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("HEY! What deadline??");
                        print_line();
                        break;
                    }
                    print_line();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(data.get(data.size()-1).toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    print_line();
                    break;

                case "event":
                    try{
                        arguments = parts[1].split(" /(from |to )");
                        data.add(new Event(arguments[0],arguments[1],arguments[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("HEY! What event??");
                        print_line();
                        break;
                    }
                    print_line();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(data.get(data.size()-1).toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    print_line();
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    System.out.println(parts[0] + "? I have no clue what you're talking about buddy.");
                    System.out.println(">:(");
                    print_line();
            }
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