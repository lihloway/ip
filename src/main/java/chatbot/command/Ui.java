package chatbot.command;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    protected Scanner scanner;
    public Ui(InputStream in) {
        scanner = new Scanner(in);
    }

    /**
     * Reads the next line from the InputStream
     *
     * This method runs the function nextLine() on the InputStream provided
     * in the constructor. \
     *
     * @return the String representing message in the next line
     */
    public String readNext(){
        return scanner.nextLine();
    }
}
