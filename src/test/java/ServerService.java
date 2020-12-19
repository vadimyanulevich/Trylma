import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerService {
    Server server;
    // Sprawdzanie obsługi klienta, gdy serwer nie działa
    @Test
    public void noServerNoConnection() {
        Client cl = new Client();
        assertFalse(cl.isConnected());
    }

    // Sprawdzanie, że klient jest podłączony do serwera
    @Test
    public void serverConnection() throws IOException {
        boolean test;
        ServerThread serverThread = new ServerThread();
        serverThread.start();

        Client cl = new Client();
        test = cl.isConnected();
        serverThread.terminate();

        assertTrue(test);
    }

    // testuje zachowanie klienta po nagłej awarii serwera
    @Test
    public void serverCrash() throws IOException {
        boolean test;
        ServerThread serverThread = new ServerThread();
        serverThread.start();
        ClientThread clientThread = new ClientThread();
        clientThread.start();

        test = clientThread.isConnected();
        serverThread.terminate();

        System.out.println(test);
        test = !test || clientThread.isConnected();
        clientThread.terminate();
        assertFalse(test);
    }

    //Test sprawdza, że po podłączeniu 10 clientów do serwera, każdy z nich jest podłączony,
    // a po odłączeniu jest odłączony
    @Test
    public void multipleClientsHandle() throws IOException, InterruptedException {
        boolean test1, test2;
        ServerThread serverThread = new ServerThread();
        serverThread.start();
        ClientThread[] clients = new ClientThread[10];

        for(int i = 0; i < 10; i++) {
            clients[i] = new ClientThread();
        }

        //sleep(1000);
        test1 = clients[0].isConnected();
        for(int i = 1; i < 10; i++) {
            test1 = test1 && clients[i].isConnected();
        }
        //zakończmy wątki klientów powiedzmy zerowego i dziewiątego
        clients[0].terminate();
        clients[9].terminate();
        //teraz wyłączmy serwer
        serverThread.terminate();
        sleep(1000);
        //sprawdzamy stan połączenia aktywnych klientów
        System.out.print("Klient " + 1 + ": ");
        test2 = clients[1].isConnected();
        clients[1].terminate();
        for(int i = 2; i < 9; i++) {
            System.out.print("Klient " + i + ": ");
            test2 = test2 || clients[i].isConnected();
            clients[i].terminate();
        }
        assertTrue(test1 && !test2);
    }

    public class ServerThread extends Thread {
        private Server server;

        public ServerThread() throws IOException {
            server = new Server();
        }

        @Override
        public void run(){
            try {
                while (true) {
                    server.handleClient();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Server getServer() {
            return this.server;
        }

        public void terminate() throws IOException {
            this.getServer().getServerSocket().close();
        }
    }

    public class ClientThread extends Thread {
        private boolean exit;
        private Client client;

        public ClientThread() {
            exit = false;
            client = new Client();
        }

        @Override
        public void run(){
            while (!exit) {
                client.isConnected();
            }
        }

        public void terminate() {
            exit = true;
        }

        public boolean isConnected() {
            return client.isConnected();
        }
    }
}
