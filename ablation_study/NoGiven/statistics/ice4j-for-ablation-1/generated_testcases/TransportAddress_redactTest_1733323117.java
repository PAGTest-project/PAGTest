
package org.ice4j;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.ice4j.ice.AgentConfig;

import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.Inet6Address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransportAddress_redactTest {

    @Test
    public void testRedactWithRedactionEnabled() throws Exception {
        // Given
        InetAddress mockAddr = mock(Inet6Address.class);
        when(mockAddr.getAddress()).thenReturn(new byte[]{0x20, 0x01, 0x0d, 0xb8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        when(mockAddr.isAnyLocalAddress()).thenReturn(false);
        when(mockAddr.isLoopbackAddress()).thenReturn(false);
        when(mockAddr.getHostAddress()).thenReturn("2001:db8::");

        AgentConfig.config = mock(AgentConfig.class);
        when(AgentConfig.config.getRedactRemoteAddresses()).thenReturn(true);

        // When
        String result = TransportAddress.redact(mockAddr);

        // Then
        assertEquals("2xxx::xxx", result);
    }

    @Test
    public void testRedactWithRedactionDisabled() throws Exception {
        // Given
        InetAddress mockAddr = mock(Inet4Address.class);
        when(mockAddr.getHostAddress()).thenReturn("192.168.1.1");

        AgentConfig.config = mock(AgentConfig.class);
        when(AgentConfig.config.getRedactRemoteAddresses()).thenReturn(false);

        // When
        String result = TransportAddress.redact(mockAddr);

        // Then
        assertEquals("192.168.1.1", result);
    }
}
