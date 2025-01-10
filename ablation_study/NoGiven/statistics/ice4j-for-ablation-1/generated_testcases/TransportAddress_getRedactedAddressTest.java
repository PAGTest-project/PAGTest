
package org.ice4j;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransportAddress_getRedactedAddressTest {

    @Test
    public void testGetRedactedAddress_RedactEnabled_AddressNotNull() {
        // Given
        TransportAddress transportAddress = Mockito.spy(new TransportAddress("localhost", 8080, Transport.UDP));
        InetAddress mockAddress = mock(InetAddress.class);
        when(mockAddress.getHostAddress()).thenReturn("192.168.1.1");
        when(transportAddress.getAddress()).thenReturn(mockAddress);
        when(transportAddress.toRedactedString(mockAddress)).thenReturn("xx.xx.xx.xx");

        // When
        String result = transportAddress.getRedactedAddress();

        // Then
        assertEquals("xx.xx.xx.xx", result);
    }

    @Test
    public void testGetRedactedAddress_RedactEnabled_AddressNull() {
        // Given
        TransportAddress transportAddress = Mockito.spy(new TransportAddress("localhost", 8080, Transport.UDP));
        when(transportAddress.getAddress()).thenReturn(null);

        // When
        String result = transportAddress.getRedactedAddress();

        // Then
        assertEquals(null, result);
    }

    @Test
    public void testGetRedactedAddress_RedactDisabled() {
        // Given
        TransportAddress transportAddress = Mockito.spy(new TransportAddress("localhost", 8080, Transport.UDP));
        InetAddress mockAddress = mock(InetAddress.class);
        when(mockAddress.getHostAddress()).thenReturn("192.168.1.1");
        when(transportAddress.getAddress()).thenReturn(mockAddress);

        // When
        String result = transportAddress.getRedactedAddress();

        // Then
        assertEquals("192.168.1.1", result);
    }
}
