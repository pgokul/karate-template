package examples;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

class ExamplesTest {

    private static int findFreePort() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(0);
            socket.setReuseAddress(true);
            final int port = socket.getLocalPort();
            try {
                socket.close();
                socket = null;
            } catch (final IOException e) {
                // Ignore IOException on close()
            }
            return port;
        } catch (final IOException e) {
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (final IOException e) {
                }
            }
        }
        throw new IllegalStateException("Could not find a free TCP/IP port");
    }

    @Test
    void testParallel() {
        final var mockServerPort = findFreePort();
        System.setProperty("mockserver.port", String.valueOf(mockServerPort));
        Results results = Runner.path("classpath:examples")
                //.outputCucumberJson(true)
                .parallel(5);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
