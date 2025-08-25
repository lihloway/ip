import java.util.Scanner;

public class Harry {
    public static void main(String[] args) {
        Harry chatbot = new Harry();
    }

    Harry() {
        Scanner scanner = new Scanner(System.in);
        say_hello();
        boolean exit = false;
        String name = "";
        while(!exit){
            name = scanner.nextLine();
            if (name.equals("bye")){
                exit = true;
            }
            else{
                print_line();
                System.out.println("Huh? "+name+"?!");
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