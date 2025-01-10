
package org.ice4j;

import org.junit.jupiter.api.Test;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransportAddress_toRedactedStringTest {

    @Test
    public void testToRedactedString_NullAddress() {
        String result = TransportAddress.toRedactedString(null);
        assertEquals(null, result);
    }

    @Test
    public void testToRedactedString_AnyLocalAddress() throws UnknownHostException {
        InetAddress addr = InetAddress.getByName("0.0.0.0");
        String result = TransportAddress.toRedactedString(addr);
        assertEquals(addr.getHostAddress(), result);
    }

    @Test
    public void testToRedactedString_LoopbackAddress() throws UnknownHostException {
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        String result = TransportAddress.toRedactedString(addr);
        assertEquals(addr.getHostAddress(), result);
    }

    @Test
    public void testToRedactedString_IPv6GlobalRoutableAddress() throws UnknownHostException {
        InetAddress addr = InetAddress.getByName("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
        String result = TransportAddress.toRedactedString(addr);
        assertEquals("2xxx::xxx", result);
    }

    @Test
    public void testToRedactedString_IPv6OtherAddress() throws UnknownHostException {
        InetAddress addr = InetAddress.getByName("fc00::1");
        String result = TransportAddress.toRedactedString(addr);
        assertEquals("fc00::xxx", result);
    }

    @Test
    public void testToRedactedString_IPv4Address() throws UnknownHostException {
        InetAddress addr = InetAddress.getByName("192.168.1.1");
        String result = TransportAddress.toRedactedString(addr);
        assertEquals("xx.xx.xx.xx", result);
    }

    @Test
    public void testToRedactedString_UnknownAddressType() throws UnknownHostException {
        InetAddress addr = mock(InetAddress.class);
        when(addr.getHostAddress()).thenReturn("unknown");
        String result = TransportAddress.toRedactedString(addr);
        assertEquals("unknown", result);
    }
}
