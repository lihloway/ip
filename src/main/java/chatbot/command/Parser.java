package chatbot.command;

import java.util.Map;
import java.util.function.Consumer;

public class Parser {
    private final Map<String, Consumer<String[]>> commands;
    public Parser(Map<String, Consumer<String[]>> commands) {
        this.commands = commands;
    }

    /**
     * Returns the command associated with the given input string.
     *
     * This method looks up the input in the command map given during construction.
     * If a command is found, it returns that command. Otherwise, it returns
     * the command mapped to the empty string ("").
     *
     * @param input the input string representing the command
     * @return the Consumer representing the command to be executed
     */

    public Consumer<String[]> getCommand(String... input) {
        Consumer<String[]> command = commands.get(input[0]);
        if (command != null) {
            return command;
        }
        return commands.get("");
    }
}
