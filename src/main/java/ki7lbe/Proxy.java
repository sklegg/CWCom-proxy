package ki7lbe;


import java.io.IOException;
import java.net.InetSocketAddress;

public class Proxy {

    public static void main(String[] args) {
        logParams(args);

        InetSocketAddress localAddress = new InetSocketAddress(args[0], Integer.parseInt(args[1]));
        InetSocketAddress remoteAddress = new InetSocketAddress(args[2], Integer.parseInt(args[3]));

        ProxyThread localThread = new ProxyThread(localAddress.getPort());
        localThread.run();

        ProxyThread remoteThread = new ProxyThread(remoteAddress.getPort());
        remoteThread.run();

    }

    private static void logParams(String[] params) {
        // TODO: use logger instead

        System.out.print("-- IN --");
        System.out.print("host: " + params[0] + '\t');
        System.out.print("port: " + params[1]);

        System.out.println();
        System.out.print("-- OUT --");
        System.out.print("host: " + params[2] + '\t');
        System.out.print("port: " + params[3]);
        System.out.println();
    }
}