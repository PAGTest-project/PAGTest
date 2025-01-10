
package org.ice4j;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.Inet6Address;
import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransportAddress_getHostAddressTest {

    @Test
    public void testGetHostAddress_IPv4() throws Exception {
        InetAddress mockAddr = Mockito.mock(InetAddress.class);
        when(mockAddr.getHostAddress()).thenReturn("192.168.1.1");

        TransportAddress transportAddress = new TransportAddress(mockAddr, 8080, Transport.UDP);
        assertEquals("192.168.1.1", transportAddress.getHostAddress());
    }

    @Test
    public void testGetHostAddress_IPv6() throws Exception {
        InetAddress mockAddr = Mockito.mock(InetAddress.class);
        when(mockAddr.getHostAddress()).thenReturn("2001:db8::1");

        TransportAddress transportAddress = new TransportAddress(mockAddr, 8080, Transport.UDP);
        assertEquals("2001:db8::1", transportAddress.getHostAddress());
    }

    @Test
    public void testGetHostAddress_NullAddress() {
        TransportAddress transportAddress = new TransportAddress((InetAddress) null, 8080, Transport.UDP);
        assertEquals(null, transportAddress.getHostAddress());
    }
}
