package chatbot.command;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    protected Scanner scanner;
    public Ui(InputStream in) {
        scanner = new Scanner(in);
    }
    
    public String readNext(){
        return scanner.nextLine();
    }
}
