import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    protected Scanner scanner;
    public Ui(InputStream in) {
        scanner = new Scanner(in);
    }
    
    protected String readNext(){
        return scanner.nextLine();
    }
}
