import commands.Command;
import commands.HelpCommand;
import commands.History;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Client {
    public static void main(String[] args) throws IOException {

        DatagramChannel channel = DatagramChannel.open();
        InetSocketAddress address = new InetSocketAddress("localhost", 1235);

        History hist = new History();
        hist.addCommand("Michelle");
        hist.addCommand("is");
        hist.addCommand("the");
        hist.addCommand("best");

        ByteArrayOutputStream writebuf = new ByteArrayOutputStream(1024);
        ObjectOutputStream writeOb = new ObjectOutputStream(writebuf);

        writeOb.writeObject(hist);

        ByteBuffer buf = ByteBuffer.wrap(writebuf.toByteArray());
        channel.send(buf, address);
        hist.printHistory();
    }
}
