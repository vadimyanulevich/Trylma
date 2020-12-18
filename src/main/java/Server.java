import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    private Socket socket;
    private ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(51715);
    }

    public void run() throws IOException {
        while (true)
        {
            this.handleClient();
        }
    }

    public void handleClient() throws IOException {
        // socket object to receive incoming client requests
        socket = serverSocket.accept();
        System.out.println("A new client is connected : " + socket);
        // create a new thread object
        new ClientHandler(socket).start();
    }

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.run();
    }
}