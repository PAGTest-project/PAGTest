
package org.ice4j.ice.harvest;

import org.ice4j.TransportAddress;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SinglePortUdpHarvester_createHarvestersTest {

    @Test
    public void testCreateHarvesters_Success() throws IOException {
        // Given
        TransportAddress mockAddress = mock(TransportAddress.class);
        AbstractUdpListener.getAllowedAddresses = mock(AbstractUdpListener.getAllowedAddresses.class);
        when(AbstractUdpListener.getAllowedAddresses(anyInt())).thenReturn(List.of(mockAddress));

        // When
        List<SinglePortUdpHarvester> harvesters = SinglePortUdpHarvester.createHarvesters(12345);

        // Then
        assertEquals(1, harvesters.size());
        assertTrue(harvesters.get(0) instanceof SinglePortUdpHarvester);
    }

    @Test
    public void testCreateHarvesters_IOException() throws IOException {
        // Given
        TransportAddress mockAddress = mock(TransportAddress.class);
        AbstractUdpListener.getAllowedAddresses = mock(AbstractUdpListener.getAllowedAddresses.class);
        when(AbstractUdpListener.getAllowedAddresses(anyInt())).thenReturn(List.of(mockAddress));

        doThrow(new IOException("Mocked IOException")).when(mockAddress).getHostAddress();

        // When
        List<SinglePortUdpHarvester> harvesters = SinglePortUdpHarvester.createHarvesters(12345);

        // Then
        assertEquals(0, harvesters.size());
    }
}
