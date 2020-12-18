import java.io.*;
import java.net.*;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class Client {
    int PORT = 51715;
    Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean isConnected;

    public void connect() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            isConnected = true;
            System.out.println("Client connected");
        } catch (IOException e) {
            isConnected = false;
            System.out.println("Connection failed");
        }
    }

    public void sendIntToServer(int num) throws IOException {
        output.writeInt(num);
    }

    public int getServerResponse() {
        try {
            return input.readInt();
        } catch (IOException e) {
            return -1;
        }
    }

    private int pingServer() throws IOException {
        output.writeInt(1111);
        return input.readInt();
    }

    public boolean isConnected() throws IOException {
        if (this.isConnected && pingServer() != 9999) {
            this.isConnected = false;
        }
        return this.isConnected;
    }
}
