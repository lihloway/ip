import java.util.Scanner;

public class Harry {
    protected Scanner scanner;
    protected ListStorage data;

    public static void main(String[] args) {
        Harry chatbot = new Harry();
        chatbot.begin_interact();
    }

    Harry() {
        scanner = new Scanner(System.in);
        data = new ListStorage();
    }

    protected void begin_interact() {
        String input = "";
        boolean exit = false;
        say_hello();

        while(!exit){
            input = scanner.nextLine();
            String[] parts = input.split(" ",2);
            String[] arguments;
        switch (parts[0]) {
            case "mark":
                try {
                    int item = Integer.parseInt(parts[1]) - 1;
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
                break;
            case "unmark":
                try {
                    int item = Integer.parseInt(parts[1]) - 1;
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
                break;
            case "delete":
                try {
                    int item = Integer.parseInt(parts[1]) - 1;
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
                break;
            case "list":
                print_line();
                System.out.println(Response.LIST_TASKS.getMessage());
                for (int index = 1; index < data.getSize() + 1; index++){
                    System.out.println(index + ". " + data.print(index - 1));
                }
                if (data.getSize() == 0){
                    System.out.println(Response.LIST_FAILURE.getMessage());
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
                System.out.println(Response.TASK_SUCCESS.getMessage());
                System.out.println(data.printLast());
                System.out.println("Now you have " + data.getSize() + " tasks in the list.");
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
                System.out.println(Response.TASK_SUCCESS.getMessage());
                System.out.println(data.printLast());
                System.out.println("Now you have " + data.getSize() + " tasks in the list.");
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
                System.out.println(Response.TASK_SUCCESS.getMessage());
                System.out.println(data.printLast());
                System.out.println("Now you have " + data.getSize() + " tasks in the list.");
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