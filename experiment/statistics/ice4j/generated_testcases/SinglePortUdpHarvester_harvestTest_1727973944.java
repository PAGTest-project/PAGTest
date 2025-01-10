
package org.ice4j.ice.harvest;

import org.ice4j.ice.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SinglePortUdpHarvester_harvestTest {

    @Test
    public void testHarvestSingleComponentSingleStream() {
        // Given
        SinglePortUdpHarvester harvester = new SinglePortUdpHarvester(null);
        Agent agent = mock(Agent.class);
        IceMediaStream stream = mock(IceMediaStream.class);
        Component component = mock(Component.class);
        String ufrag = "testUfrag";

        when(component.getParentStream()).thenReturn(stream);
        when(stream.getParentAgent()).thenReturn(agent);
        when(agent.getLocalUfrag()).thenReturn(ufrag);
        when(stream.getComponentCount()).thenReturn(1);
        when(agent.getStreamCount()).thenReturn(1);

        // When
        Collection<LocalCandidate> result = harvester.harvest(component);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.iterator().next() instanceof SinglePortUdpHarvester.MyCandidate);
    }

    @Test
    public void testHarvestMultipleComponentsOrStreams() {
        // Given
        SinglePortUdpHarvester harvester = new SinglePortUdpHarvester(null);
        Agent agent = mock(Agent.class);
        IceMediaStream stream = mock(IceMediaStream.class);
        Component component = mock(Component.class);

        when(component.getParentStream()).thenReturn(stream);
        when(stream.getParentAgent()).thenReturn(agent);
        when(stream.getComponentCount()).thenReturn(2); // Multiple components
        when(agent.getStreamCount()).thenReturn(1);

        // When
        Collection<LocalCandidate> result = harvester.harvest(component);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
