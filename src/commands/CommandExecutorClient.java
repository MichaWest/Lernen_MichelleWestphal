package commands;

import collection.PersonCollection;
import exceptions.*;
import fileManager.FileWorker;
import inputManager.InputAll;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class CommandExecutorClient {

    static protected InetSocketAddress address = new InetSocketAddress("localhost", 2003);
    private PersonCollection collection;
    private InputAll input;
    //private final FileWorker fileWorker;
    private boolean run;
    private static Stack<String> runFiles = new Stack<>();
    private final List<String> history;
    private String currentScriptFileName;
    private final String[] commands= {"help","info", "show", "add", "remove_by_id", "update_by_id", "clear", "save", "execute_script", "exit", "remove_first", "reorder", "history", "group_counting_by_nationality", "count_by_hair_color" };


    public CommandExecutorClient(InputAll iManager){
        this.collection = new PersonCollection();
        this.input = iManager;
        //this.fileWorker = fManager;
        history = new ArrayList<>();
    }

    public void consoleMode(){
        try (DatagramChannel channelToRead = DatagramChannel.open()){
            run = true;
            channelToRead.bind(address);
            while (run) {
                System.out.println("Введите команду. Чтобы получит список команд введите \"help\"");
                CommandWrapper command = input.readCommand();
                runCommand(command, channelToRead);
            }
        //}catch() {
        }catch (IOException e){
            System.out.println("Не удалось установить соединение");
            e.printStackTrace();
        }
    }

    //Выполняем команду
    private void runCommand(CommandWrapper command, DatagramChannel channelToRead) throws IOException {
        try {
            if (!hasCommand(command.getCom())) {
                throw new NoSuchCommandException();
            }
            sendCommand(command.getCom());
            getResult(channelToRead);
        }catch(CommandException e){
            System.out.println(e.getMessage());
        } catch (RecursiveException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    //Проверить есть ли команда
    private boolean hasCommand(String com) {
        for(String i: commands){
            if(com.equals(i)){
                return true;
            }
        }
        return false;
    }

    //Получит результат выполнения команды
    private void getResult(DatagramChannel channelToRead ) throws IOException, ClassNotFoundException {
        ByteBuffer bufToRead = ByteBuffer.allocate(1024*1024);
        channelToRead.receive(bufToRead);
        ByteArrayInputStream readbuf = new ByteArrayInputStream(bufToRead.array());
        ObjectInputStream readOb = new ObjectInputStream(readbuf);
        Command result = (Command) readOb.readObject();
        updateData(result);
        result.getResult();
    }

    //Отправляем запрос на команду
    private void sendCommand(String com) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        ByteArrayOutputStream writebuf = new ByteArrayOutputStream(1024 * 1024);
        ObjectOutputStream writeOb = new ObjectOutputStream(writebuf);
        ByteBuffer buf;
        switch (com) {
            case ("help"):
                HelpCommand help = new HelpCommand();
                writeOb.writeObject(help);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("history"):
                HistoryCommand history = new HistoryCommand();
                writeOb.writeObject(history);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("info"):
                InfoCommand info = new InfoCommand();
                writeOb.writeObject(info);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("show"):
                ShowCommand show = new ShowCommand();
                writeOb.writeObject(show);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("add"):
                AddCommand add = new AddCommand();
                writeOb.writeObject(add);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("remove_by_id"):
                RemoveByIdCommand removeById = new RemoveByIdCommand();
                writeOb.writeObject(removeById);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("update_by_id"):
                UpdateByIdCommand updateById = new UpdateByIdCommand();
                writeOb.writeObject(updateById);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("clear"):
                ClearCommand clear = new ClearCommand();
                writeOb.writeObject(clear);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("save"):
                SaveCommand save = new SaveCommand();
                writeOb.writeObject(save);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("execute_script"):
                ExecuteScriptCommand executeScript = new ExecuteScriptCommand();
                writeOb.writeObject(executeScript);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("remove_first"):
                RemoveFirstCommand removeFirst = new RemoveFirstCommand();
                writeOb.writeObject(removeFirst);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("exit"):
                System.out.println("Good bye");
                run = false;
                break;
            case ("reorder"):
                ReorderCommand reorder = new ReorderCommand();
                writeOb.writeObject(reorder);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("min_by_weight"):
                MinByWeightCommand minByWeight = new MinByWeightCommand();
                writeOb.writeObject(minByWeight);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("group_counting_by_nationality"):
                GroupCountingByNationalityCommand groupCountingByNationality = new GroupCountingByNationalityCommand();
                writeOb.writeObject(groupCountingByNationality);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
            case ("count_by_hair_color"):
                CountByHairColorCommand countByHairColor = new CountByHairColorCommand();
                writeOb.writeObject(countByHairColor);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                channel.send(buf, address);
                writeOb.flush();
                break;
        }
        channel.close();
    }

    //Обновляем значение коллекции
    public void updateData(Command result){
        this.collection = result.getCollection();
    }

}
