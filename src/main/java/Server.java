import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

    DataInputStream input;
    DataOutputStream output;

    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(51715);

        System.out.println("Waiting for client...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public void run() throws IOException {

        while (true) {
            int num = input.readInt();
            output.writeInt(num*2);
        }
    }

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.run();
    }
    /*public static void main(String[] args) throws IOException {
        final int PORT = 51715;
        int number;
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();

        // przychwytywanie informacji od gracza
        Scanner scanner = new Scanner(socket.getInputStream());

        PrintStream p = new PrintStream(socket.getOutputStream());
        while (true) {
            number = scanner.nextInt();
            number *= 2;
            p.println(number);
        }
    }*/
}