import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    int PORT = 51715;
    int TIMEOUT = 3000;
    Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean isConnected;

    public Client() {
        this.connect();
    }

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
        sendIntToServer(1111);
        return getServerResponse();
    }

    public boolean isConnected() {
        this.isConnected = false;
        SocketAddress socketAddress;
        try {
            socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), PORT);
            Socket newSocket = new Socket();
            newSocket.connect(socketAddress, TIMEOUT);
            newSocket.close();
            this.isConnected = true;
        } catch (SocketTimeoutException socketTimeoutException) {
            System.out.println("SocketTimeoutException");
        } catch (IOException e) {
            System.out.println("Unable to connect to the socket");
        }
        return this.isConnected;
    }
}
