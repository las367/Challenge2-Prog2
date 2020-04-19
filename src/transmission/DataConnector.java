package transmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class DataConnector implements DataConnection {

    private Socket socket = null;
    private ServerSocket server = null;

    /**
     * Create client side - open connection to address / port
     * @param address
     */
    public DataConnector(String address, int port) {

        try {
            socket = new Socket(address, port);
        } catch (UnknownHostException ex) {
            System.out.println("Host is unknown");
        } catch (IOException ex) {
            System.out.println("Something unexpected occurred..");
        }
    }

    /**
     * Create server side - open port on this port and wait for one client
     * @param port
     */
    public DataConnector(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server is up, waiting for client");

            socket = server.accept();
        } catch (IOException e) {
            System.out.println("Something unexpected occurred");
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
