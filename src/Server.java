import collection.PersonCollection;
import commands.Command;
import commands.CommandExecutorClient;
import commands.CommandExecutorServer;
import commands.HelpCommand;
import fileManager.FileWorker;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;


public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PersonCollection personCollection = new PersonCollection();
        FileWorker fileWorker = new FileWorker();
        String path = "MICHELLE";
        try {
            fileWorker.setPath(path);
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        if(!personCollection.deserializeCollection(fileWorker.read())){
            System.out.println("В файле ошибка");
            personCollection.clear();
        }
        CommandExecutorServer command = new CommandExecutorServer(fileWorker, personCollection);
        command.serverMode();

    }

}
