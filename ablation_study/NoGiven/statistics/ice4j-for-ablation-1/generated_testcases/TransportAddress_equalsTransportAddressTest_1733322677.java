
package org.ice4j;

import org.ice4j.ice.Transport;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransportAddress_equalsTransportAddressTest {

    @Test
    public void testEqualsTransportAddress_SameTransport() {
        TransportAddress addr1 = new TransportAddress("localhost", 8080, Transport.UDP);
        TransportAddress addr2 = new TransportAddress("localhost", 8080, Transport.UDP);
        assertTrue(addr1.equalsTransportAddress(addr2));
    }

    @Test
    public void testEqualsTransportAddress_DifferentTransport() {
        TransportAddress addr1 = new TransportAddress("localhost", 8080, Transport.UDP);
        TransportAddress addr2 = new TransportAddress("localhost", 8080, Transport.TCP);
        assertFalse(addr1.equalsTransportAddress(addr2));
    }

    @Test
    public void testEqualsTransportAddress_DifferentAddress() {
        TransportAddress addr1 = new TransportAddress("localhost", 8080, Transport.UDP);
        TransportAddress addr2 = new TransportAddress("127.0.0.1", 8080, Transport.UDP);
        assertFalse(addr1.equalsTransportAddress(addr2));
    }
}
