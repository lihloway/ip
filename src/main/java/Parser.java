import java.util.Map;
import java.util.function.Consumer;

public class Parser {
    private final Map<String, Consumer<String[]>> commands;
    public Parser(Map<String, Consumer<String[]>> commands) {
        this.commands = commands;
    }
    protected Consumer<String[]> getCommand(String input){
        Consumer<String[]> command = commands.get(input);
        if (command != null){
            return command;
        }
        return commands.get("");
    }
}
