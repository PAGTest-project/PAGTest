
package org.ice4j.ice.harvest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.Transport;
import org.ice4j.TransportAddress;

public class SinglePortUdpHarvester_getHarvestStatisticsTest {

    @Test
    public void testGetHarvestStatistics() {
        SinglePortUdpHarvester harvester = new SinglePortUdpHarvester(new TransportAddress("0.0.0.0", 0, Transport.UDP));
        assertNotNull(harvester.getHarvestStatistics());
    }
}
