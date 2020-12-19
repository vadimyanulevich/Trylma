import java.io.IOException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Game {
    boolean isMatchInProcess;

    public Game() {}

    public void begin() {
        /*
            Tu będzie logika matchmakingu
         */

        //tymczasowe do sprawdzenia działania komunikacji z serwerem
        Scanner sc = new Scanner(System.in);

        Client client = new Client();
        isMatchInProcess = true;
        int num;

        while(isMatchInProcess) {
            num = sc.nextInt();
            if(client.sendIntToServer(num)) {
                System.out.println(client.getServerResponse());
            }
            else {
                while(!client.isConnected()) {
                    client.connect();
                }
                System.out.println("The connection has been recovered");
            }
        }

    }
}
