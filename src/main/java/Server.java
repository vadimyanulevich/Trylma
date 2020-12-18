import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(51715);
    }

    public void run() throws IOException {
        while (true)
        {
            Socket socket;
            // socket object to receive incoming client requests
            socket = serverSocket.accept();
            System.out.println("A new client is connected : " + socket +
                    "\nAssigning new thread for this client");
            // create a new thread object
            Thread t = new ClientHandler(socket);
            // Invoking the start() method
            t.start();
        }
    }

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.run();
    }
}