import java.util.Scanner;

public class Harry {
    protected Scanner scanner;
    protected Task[] data;
    protected int pointer;

    public static void main(String[] args) {
        Harry chatbot = new Harry();
    }

    Harry() {
        scanner = new Scanner(System.in);
        data = new Task[100];
        int pointer = 0;
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
                        print_line();
                        data[item].complete();
                        System.out.println("Nice! I've marked this task as done:\n" + data[item].toString());
                        print_line();
                    } catch (NumberFormatException e) {
                        print_line();
                        System.out.println("Error: invalid item index given");
                        print_line();
                    }
                    break;
                case "unmark":
                    try {
                        int item = Integer.parseInt(parts[1]) - 1;
                        print_line();
                        data[item].uncomplete();
                        System.out.println("OK, I've marked this task as not done yet:\n" + data[item].toString());
                        print_line();
                    } catch (NumberFormatException e) {
                        print_line();
                        System.out.println("Error: invalid item index given");
                        print_line();
                    }
                    break;
                case "list":
                    print_line();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < pointer + 1; i++){
                        System.out.println(i + ". " + data[i-1].toString());
                    }
                    print_line();
                    break;
                case "todo":
                    print_line();
                    data[pointer] = new ToDo(parts[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(data[pointer].toString());
                    pointer++;
                    System.out.println("Now you have "+pointer+" tasks in the list.");
                    print_line();
                    break;

                case "deadline":
                    arguments = parts[1].split(" /by ");
                    print_line();
                    data[pointer] = new Deadline(arguments[0],arguments[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(data[pointer].toString());
                    pointer++;
                    System.out.println("Now you have "+pointer+" tasks in the list.");
                    print_line();
                    break;

                case "event":
                    arguments = parts[1].split(" /(from |to )");
                    print_line();
                    data[pointer] = new Event(arguments[0],arguments[1],arguments[2]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(data[pointer].toString());
                    pointer++;
                    System.out.println("Now you have "+pointer+" tasks in the list.");
                    print_line();
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    System.out.println("Error: invalid input");
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
        return " _    _                          \n"
                + "| |  | |                  @     \n"
                + "| |__| | ____ ____ ____   _   \n"
                + "|  __  |/ _  |  __|  __| | |   \n"
                + "| |  | | (_| | |  | |    | | \n"
                + "|_|  |_|\\__/_|_|  |_|    |_|  \n";
    }
}