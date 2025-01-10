
package org.ice4j.ice.harvest;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.ice.*;
import org.junit.jupiter.api.*;
import java.net.*;
import java.util.*;

public class SinglePortUdpHarvester_harvestTest {

    private SinglePortUdpHarvester harvester;
    private Component component;
    private IceMediaStream stream;
    private Agent agent;

    @BeforeEach
    public void setUp() throws Exception {
        TransportAddress address = new TransportAddress("127.0.0.1", 0, Transport.UDP);
        harvester = new SinglePortUdpHarvester(address);

        agent = new Agent();
        stream = new IceMediaStream("testStream", agent);
        component = new Component(stream, Component.RTP);
    }

    @Test
    public void testHarvestSingleComponentSingleStream() {
        agent.addStream(stream);
        stream.addComponent(component);

        Collection<LocalCandidate> candidates = harvester.harvest(component);

        assertNotNull(candidates);
        assertEquals(1, candidates.size());
        assertTrue(candidates.iterator().next() instanceof LocalCandidate);
    }

    @Test
    public void testHarvestMultipleComponents() {
        agent.addStream(stream);
        stream.addComponent(component);
        stream.addComponent(new Component(stream, Component.RTCP));

        Collection<LocalCandidate> candidates = harvester.harvest(component);

        assertNotNull(candidates);
        assertTrue(candidates.isEmpty());
    }

    @Test
    public void testHarvestMultipleStreams() {
        agent.addStream(stream);
        IceMediaStream anotherStream = new IceMediaStream("anotherStream", agent);
        agent.addStream(anotherStream);
        stream.addComponent(component);

        Collection<LocalCandidate> candidates = harvester.harvest(component);

        assertNotNull(candidates);
        assertTrue(candidates.isEmpty());
    }

    @AfterEach
    public void tearDown() {
        harvester.close();
    }
}
