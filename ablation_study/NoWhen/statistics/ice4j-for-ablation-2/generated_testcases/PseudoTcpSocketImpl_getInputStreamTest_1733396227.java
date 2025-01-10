
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.DatagramSocket;

public class PseudoTcpSocketImpl_getInputStreamTest {

    @Test
    public void testGetInputStream_InitialNull() throws IOException {
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345, new DatagramSocket());
        assertNull(socketImpl.inputStream);
        assertNotNull(socketImpl.getInputStream());
    }

    @Test
    public void testGetInputStream_AlreadyInitialized() throws IOException {
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345, new DatagramSocket());
        socketImpl.inputStream = new PseudoTcpInputStream();
        assertNotNull(socketImpl.getInputStream());
    }
}
