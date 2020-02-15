package ki7lbe;

import org.netcrusher.core.filter.LoggingFilter;
import org.netcrusher.core.reactor.NioReactor;
import org.netcrusher.datagram.DatagramCrusher;
import org.netcrusher.datagram.DatagramCrusherBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Proxy {

    public static void main(String[] args) {
        logParams(args);

        InetSocketAddress localAddress = new InetSocketAddress(args[0], Integer.parseInt(args[1]));
        InetSocketAddress remoteAddress = new InetSocketAddress(args[2], Integer.parseInt(args[3]));

        NioReactor reactor;

        try {
            reactor = new NioReactor();
        } catch (IOException exception) {
            return;
        }
        DatagramCrusher crusher = DatagramCrusherBuilder.builder()
                .withReactor(reactor)
                .withBindAddress(localAddress)
                .withConnectAddress(remoteAddress)
                //.withIncomingTransformFilterFactory((addr) -> new LoggingFilter(localAddress, "client", LoggingFilter.Level.DEBUG))
                //.withOutgoingTransformFilterFactory((addr) -> new LoggingFilter(remoteAddress, "server", LoggingFilter.Level.DEBUG))
                .buildAndOpen();

        /* https://github.com/NetCrusherOrg/netcrusher-java/blob/master/samples/sample-datagram-rfc868/src/test/java/org/netcrusher/DatagramCrusherRFC868Test.java */
        while (crusher.isOpen()) {
           //System.out.println("crusher loop");
            //crusher.freeze();

            //crusher.unfreeze();
        }

        System.out.println("crusher closed");
        crusher.close();
        reactor.close();

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