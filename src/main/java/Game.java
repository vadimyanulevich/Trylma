import java.io.IOException;
import java.util.Scanner;

public class Game {
    boolean isMatchInProcess;

    public Game() {}

    public void begin() throws IOException {
        System.out.println("Tak będzie działać gra");

        // logika matchmakingu

        //tymczasowe
        Scanner sc = new Scanner(System.in);

        Klient client = new Klient();
        client.connect();
        // ładowanie mapy
        isMatchInProcess = true;
        int num;

        while(isMatchInProcess) {
            num = sc.nextInt();
            client.sendIntToServer(num);
            System.out.println(client.getServerResponse());

            //System.out.println("Teraz tura");
            // oboje gracze mają własne wątki, które sprawdzają czy jest ich tura

            // najlepiej, żeby aktywny gracz robił ruch, następnie serwer dostaje info,
            // że ten gracz zakończył ruch, po czym wysyła informację do kolejnego gracza
        }

    }
}
