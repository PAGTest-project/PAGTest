
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.*;
import java.util.logging.*;
import org.junit.jupiter.api.*;

public class PseudoTcpSocketImpl_onTcpReadableTest {

    private PseudoTcpSocketImpl pseudoTcpSocketImpl;
    private PseudoTCPBase pseudoTcpBase;

    @BeforeEach
    public void setUp() throws SocketException {
        pseudoTcpSocketImpl = new PseudoTcpSocketImpl(0);
        pseudoTcpBase = pseudoTcpSocketImpl.pseudoTcp;
    }

    @Test
    public void testOnTcpReadableWithLoggable() {
        // Given
        Logger logger = Logger.getLogger(PseudoTcpSocketImpl.class.getName());
        logger.setLevel(Level.FINER);
        ByteArrayOutputStream logCapture = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(logCapture));

        // When
        pseudoTcpSocketImpl.onTcpReadable(pseudoTcpBase);

        // Then
        assertTrue(logCapture.toString().contains("TCP READABLE data available for reading: " + pseudoTcpBase.getAvailable()));
        System.setOut(originalOut);
    }

    @Test
    public void testOnTcpReadableWithoutLoggable() {
        // Given
        Logger logger = Logger.getLogger(PseudoTcpSocketImpl.class.getName());
        logger.setLevel(Level.FINE);
        ByteArrayOutputStream logCapture = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(logCapture));

        // When
        pseudoTcpSocketImpl.onTcpReadable(pseudoTcpBase);

        // Then
        assertFalse(logCapture.toString().contains("TCP READABLE data available for reading: " + pseudoTcpBase.getAvailable()));
        System.setOut(originalOut);
    }

    @Test
    public void testOnTcpReadableNotifyAll() throws InterruptedException {
        // Given
        AtomicBoolean notified = new AtomicBoolean(false);
        Thread notifyThread = new Thread(() -> {
            synchronized (pseudoTcpSocketImpl.read_notify) {
                try {
                    pseudoTcpSocketImpl.read_notify.wait();
                    notified.set(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        notifyThread.start();

        // When
        pseudoTcpSocketImpl.onTcpReadable(pseudoTcpBase);

        // Then
        notifyThread.join(1000);
        assertTrue(notified.get());
    }
}
