import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerService {
    Server server;
    // Sprawdzanie obsługi klienta, gdy serwer nie działa
    @Test
    public void noServerNoConnection() throws IOException {
        Client cl = new Client();
        cl.connect();
        assertFalse(cl.isConnected());
    }

    // Sprawdzanie, że klient jest podłączony do serwera
    @Test
    public void serverConnection() throws IOException {
        boolean test;
        ServerThread serverThread = new ServerThread();
        serverThread.start();

        Client cl = new Client();
        cl.connect();
        test = cl.isConnected();
        serverThread.terminate();

        assertTrue(test);
    }

    /*
        TU BĘDZIE TEST SPRAWDZAJĄCY OBSłUGĘ KILKU KLIENTÓW NA RAZ
    */
    //@Test

    public class ServerThread extends Thread {
        private boolean exit;
        Server server;

        public ServerThread() throws IOException {
            exit = false;
            server = new Server();
        }

        @Override
        public void run(){
            try {
                while (!exit) {
                    server.handleClient();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void terminate(){
            exit = true;
        }
    }
}
