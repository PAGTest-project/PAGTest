
package org.ice4j.ice.harvest;

import org.ice4j.TransportAddress;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SinglePortUdpHarvester_createHarvestersTest {

    @Test
    public void testCreateHarvesters_Success() throws IOException {
        // Given
        TransportAddress mockAddress = mock(TransportAddress.class);
        when(AbstractUdpListener.getAllowedAddresses(anyInt())).thenReturn(List.of(mockAddress));

        // When
        List<SinglePortUdpHarvester> result = SinglePortUdpHarvester.createHarvesters(1234);

        // Then
        assertEquals(1, result.size());
        verify(mockAddress, times(1)).getPort();
    }

    @Test
    public void testCreateHarvesters_IOException() throws IOException {
        // Given
        TransportAddress mockAddress = mock(TransportAddress.class);
        when(AbstractUdpListener.getAllowedAddresses(anyInt())).thenReturn(List.of(mockAddress));
        doThrow(new IOException("Mocked IOException")).when(mockAddress).getPort();

        // When
        List<SinglePortUdpHarvester> result = SinglePortUdpHarvester.createHarvesters(1234);

        // Then
        assertTrue(result.isEmpty());
    }
}
