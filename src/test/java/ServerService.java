import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerService {
    //Sprawdzanie obsługi klienta, gdy serwer nie działa
    @Test
    public void noServerNoConnection() throws IOException {
        Client cl = new Client();
        cl.connect();
        assertFalse(cl.isConnected());
    }

    /*//TEN TEST JESZCZE NIE DZIAłA
    @Test
    public void serverConnection() throws IOException {
        serverStart();
        Client cl = new Client();
        cl.connect();
        assertTrue(cl.isConnected());
    }*/

    /*
        TU BĘDZIE TEST SPRAWDZAJĄCY OBSłUGĘ KILKU KLIENTÓW NA RAZ
    */
    //@Test

    private void serverStart() throws IOException {
        new Server().run();
    }
}
