import collection.PersonCollection;
import commands.CommandExecutorClient;
import fileManager.FileWorker;
import inputManager.ConsoleInput;
import inputManager.InputAll;

import java.io.*;

public class Client {
    protected ObjectInputStream readOb;

    public static void main(String[] args) throws IOException {

        InputAll console = new ConsoleInput();
        CommandExecutorClient command = new CommandExecutorClient(console);
        command.consoleMode();
    }

}
