
package org.ice4j;

import org.junit.jupiter.api.Test;
import org.ice4j.ice.Transport;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class TransportAddress_canReachTest {

    @Test
    void testCanReach_SameTransportAndIPv6_SameLinkLocal() throws UnknownHostException {
        TransportAddress src = new TransportAddress(InetAddress.getByName("fe80::1"), 1234, Transport.UDP);
        TransportAddress dst = new TransportAddress(InetAddress.getByName("fe80::2"), 5678, Transport.UDP);

        assertTrue(src.canReach(dst));
    }

    @Test
    void testCanReach_DifferentTransport() throws UnknownHostException {
        TransportAddress src = new TransportAddress(InetAddress.getByName("fe80::1"), 1234, Transport.UDP);
        TransportAddress dst = new TransportAddress(InetAddress.getByName("fe80::2"), 5678, Transport.TCP);

        assertFalse(src.canReach(dst));
    }

    @Test
    void testCanReach_SameTransportDifferentIPVersion() throws UnknownHostException {
        TransportAddress src = new TransportAddress(InetAddress.getByName("fe80::1"), 1234, Transport.UDP);
        TransportAddress dst = new TransportAddress(InetAddress.getByName("192.168.1.1"), 5678, Transport.UDP);

        assertFalse(src.canReach(dst));
    }

    @Test
    void testCanReach_SameTransportIPv6DifferentLinkLocal() throws UnknownHostException {
        TransportAddress src = new TransportAddress(InetAddress.getByName("fe80::1"), 1234, Transport.UDP);
        TransportAddress dst = new TransportAddress(InetAddress.getByName("2001:db8::1"), 5678, Transport.UDP);

        assertFalse(src.canReach(dst));
    }

    @Test
    void testCanReach_SameTransportIPv6DifferentLinkLocal_AllowLinkToGlobal() throws UnknownHostException {
        System.setProperty(StackProperties.ALLOW_LINK_TO_GLOBAL_REACHABILITY, "true");
        TransportAddress src = new TransportAddress(InetAddress.getByName("fe80::1"), 1234, Transport.UDP);
        TransportAddress dst = new TransportAddress(InetAddress.getByName("2001:db8::1"), 5678, Transport.UDP);

        assertTrue(src.canReach(dst));
    }
}
