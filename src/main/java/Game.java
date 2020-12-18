import java.io.IOException;
import java.util.Scanner;

public class Game {
    boolean isMatchInProcess;

    public Game() {}

    public void begin() throws IOException {
        /*
            Tu będzie logika matchmakingu
         */

        //tymczasowe do sprawdzenia działania komunikacji z serwerem
        Scanner sc = new Scanner(System.in);

        Client client = new Client();
        client.connect();
        isMatchInProcess = true;
        int num;

        while(isMatchInProcess) {
            num = sc.nextInt();
            client.sendIntToServer(num);
            System.out.println(client.getServerResponse());
        }

    }
}
