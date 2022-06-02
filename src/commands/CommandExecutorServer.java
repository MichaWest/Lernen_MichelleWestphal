package commands;

import collection.PersonCollection;
import fileManager.FileWorker;
import inputManager.InputAll;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandExecutorServer {

    static protected InetSocketAddress address = new InetSocketAddress("localhost", 2003);
    private PersonCollection collection;
    //private InputAll input;
    private final FileWorker fileWorker;
    private boolean run;
    private static Stack<String> runFiles = new Stack<>();
    private final List<String> history;
    private String currentScriptFileName;
    private final String[] commands= {"help","info", "show", "add", "remove_by_id", "update_by_id", "clear", "save", "execute_script", "exit", "remove_first", "reorder", "history", "group_counting_by_nationality", "count_by_hair_color" };


    public CommandExecutorServer(FileWorker fileWorker, PersonCollection collection){
        this.collection = collection;
        this.fileWorker = fileWorker;
        history = new ArrayList<>();
    }

    public void serverMode(){
        try{
        try(DatagramChannel channel = DatagramChannel.open()) {
            channel.bind(address);
            run = true;
            while (run) {
                Command command = getCommand(channel);
                runCommand(command);
            }
            channel.close();
        }catch(ClassNotFoundException e) {
            sendResult(new Exception());
            e.printStackTrace();
        }
        }catch (IOException e){
            System.out.println("Не удалось установить соединение");
            e.printStackTrace();
        }
    }

    private Command getCommand(DatagramChannel channel) throws IOException, ClassNotFoundException {
        ByteBuffer buf = ByteBuffer.allocate(1024*1024);

        channel.receive(buf);

        ByteArrayInputStream readbuf = new ByteArrayInputStream(buf.array());
        ObjectInputStream readOb = new ObjectInputStream(readbuf);
        return (Command) readOb.readObject();
    }

    private void sendResult(Command result) throws IOException {
        DatagramChannel channel = DatagramChannel.open();;

        ByteArrayOutputStream writebuf = new ByteArrayOutputStream(1024*1024);
        ObjectOutputStream writeOb = new ObjectOutputStream(writebuf);

        writeOb.writeObject(result);
        ByteBuffer buf = ByteBuffer.wrap(writebuf.toByteArray());
        channel.send(buf, address);
        channel.close();
    }

    private void runCommand(Command command) throws IOException {
        String com = command.getNameOfCommand();
        switch(com){
            case("info"):
                InfoCommand info = (InfoCommand) command;
                info.collection = this.collection;
                sendResult(info);
                break;
            case("show"):
                break;
            case("add"):
                break;
            case("update_by_id"):
                break;
            case("remove_by_id"):
                break;
            case("clear"):
                break;
            case("save"):
                break;
            case("execute_script"):
                break;
            case("exit"):
                break;
            case("remove_first"):
                break;
            case("reorder"):
                break;
            case("history"):
                break;
            case("min_by_weight"):
                break;
            case("group_counting_by_nationality"):
                break;
            case("count_by_hair_color"):
                break;
        }
        history.add(com);
    }

}
