import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        // obtaining input and output streams
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        int num;
        while (true) {
            try {
                num = inputStream.readInt();
                if (num == 1111) {
                    outputStream.writeInt(9999);
                    continue;
                }
                outputStream.writeInt(num*2);
            } catch (IOException e) {
                System.out.println("client " + socket + " is disconnected");
                break;
            }
        }
    }
}
