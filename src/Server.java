import commands.Command;
import commands.HelpCommand;
import commands.History;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;


public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramChannel channel = DatagramChannel.open();
        InetSocketAddress address = new InetSocketAddress("localhost", 1235);
        channel.bind(address);
        ByteBuffer buf = ByteBuffer.allocate(1024);

        channel.receive(buf);

        ByteArrayInputStream readbuf = new ByteArrayInputStream(buf.array());
        ObjectInputStream readOb = new ObjectInputStream(readbuf);
        History hist = (History)readOb.readObject();
        hist.printHistory();
    }

}
