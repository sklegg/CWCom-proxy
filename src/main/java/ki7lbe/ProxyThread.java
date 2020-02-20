package ki7lbe;

import ki7lbe.protocol.ClientDatagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ProxyThread extends Thread {

    // https://stackoverflow.com/questions/10556829/sending-and-receiving-udp-packets-using-java/13780614

    private DatagramSocket clientSocket;
    private boolean running;
    private int port;
    private int DATA_LENGTH = 200;
    private byte[] incomingData = new byte[DATA_LENGTH];

    private DatagramPacket incomingPacket;
    private ClientDatagram clientDatagram = new ClientDatagram(DATA_LENGTH);

    public void run() {
        running = true;

        try {
            startSocket();
        } catch (SocketException e) {
            handleException(e);
        }

        while (running) {
            try {
                incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                clientSocket.receive(incomingPacket);

                clientDatagram.setRawData(incomingPacket.getData());
                clientDatagram.decode();

            } catch (IOException e) {
                handleException(e);
            }
        }

        clientSocket.close();
    }

    public ProxyThread(int port) {
        super();
        this.port = port;
    }

    private void startSocket() throws SocketException {
        clientSocket = new DatagramSocket(port);
    }

    private void handleException (Exception e) {
        e.printStackTrace();
        running = false;
    }
}
