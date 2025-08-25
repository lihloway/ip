import java.util.Scanner;

public class Harry {
    public static void main(String[] args) {
        Harry chatbot = new Harry();
    }

    Harry() {
        Scanner scanner = new Scanner(System.in);
        String[] data = new String[100];
        int pointer = 0;


        say_hello();
        boolean exit = false;
        String item = "";
        while(!exit){
            item = scanner.nextLine();
            switch (item) {
                case "list":
                    print_line();
                    for (int i = 1; i < pointer + 1; i++){
                        System.out.println(i + ". " + data[i-1]);
                    }
                    print_line();
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    print_line();
                    System.out.println("added: " + item);
                    data[pointer] = item;
                    pointer++;
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