
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class PseudoTcpSocket_acceptTest {
    private PseudoTcpSocket server;
    private PseudoTcpSocket client;

    @BeforeEach
    public void setUp() throws IOException {
        server = new PseudoTcpSocketFactory().createSocket();
        client = new PseudoTcpSocketFactory().createSocket();
    }

    @Test
    @Timeout(10)
    public void testAcceptWithTimeout() throws IOException, InterruptedException {
        server.setDebugName("Server");
        server.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0));
        final InetSocketAddress serverAddress = new InetSocketAddress(InetAddress.getLoopbackAddress(), server.getLocalPort());

        AtomicBoolean serverThreadEnded = new AtomicBoolean();
        Thread serverThread = new Thread(() -> {
            try {
                server.accept(5000);
                serverThreadEnded.set(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        AtomicBoolean clientThreadEnded = new AtomicBoolean();
        Thread clientThread = new Thread(() -> {
            try {
                client.setDebugName("Client");
                client.connect(serverAddress, 5000);
                clientThreadEnded.set(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        serverThread.start();
        clientThread.start();

        try {
            boolean success = assert_wait_until(() -> client.isConnected(), 5000);
            if (success) {
                clientThread.join(10_000);
                if (!clientThreadEnded.get()) {
                    fail("client thread did not end");
                }
                serverThread.join(10_000);
                if (!serverThreadEnded.get()) {
                    fail("server thread did not end");
                }
                server.close();
                client.close();
            } else {
                fail("Connection timeout");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Timeout(10)
    public void testAcceptTimeoutExpired() throws IOException, InterruptedException {
        server.setDebugName("Server");
        server.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0));

        AtomicBoolean serverThreadEnded = new AtomicBoolean();
        Thread serverThread = new Thread(() -> {
            try {
                server.accept(1000);
                serverThreadEnded.set(true);
            } catch (IOException e) {
                if (!e.getMessage().contains("timeout")) {
                    throw new RuntimeException(e);
                }
            }
        });

        serverThread.start();

        try {
            boolean success = assert_wait_until(() -> serverThreadEnded.get(), 2000);
            if (success) {
                serverThread.join(10_000);
                if (!serverThreadEnded.get()) {
                    fail("server thread did not end");
                }
                server.close();
            } else {
                fail("Server thread did not end as expected");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean assert_wait_until(java.util.function.BooleanSupplier condition, long timeout) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (!condition.getAsBoolean()) {
            if (System.currentTimeMillis() - startTime > timeout) {
                return false;
            }
            Thread.sleep(100);
        }
        return true;
    }
}
