package transmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class DataConnector implements DataConnection, Runnable {

    private Socket socket;
    private ServerSocket server;

    private String address;
    private int port;

    /**
     * Create client side - open connection to address / port
     * Change the constructor to just receive the paremeters..
     * @param address
     */
    public DataConnector(String address, int port) {

        this.address = address;
        this.port = port;
    }

    /**
     * Create server side - open port on this port and wait for one client
     * @param port
     */
    public DataConnector(int port) {

        this.port = port;
    }

    @Override
    public void run() {

        if (address == null) {

            //if address == null => create server side
            try {
                server = new ServerSocket(port);
                System.out.println("Server is up, waiting for client");

                socket = server.accept();

                System.out.println("connected");
            } catch (IOException e) {
                System.out.println("Something unexpected occurred");
            }
        } else {

            try {

                System.out.println("Searching for server..");
                socket = new Socket(address, port);

            } catch (UnknownHostException ex) {
                System.out.println("Host is unknown");
            } catch (IOException ex) {
                System.out.println("Something unexpected occurred..");
            }
        }
    }

    @Override
    public DataInputStream getDataInputStream() throws IOException {
        return new DataInputStream(socket.getInputStream());
    }

    @Override
    public DataOutputStream getDataOutputStream() throws IOException {
        return new DataOutputStream(socket.getOutputStream());
    }
}
