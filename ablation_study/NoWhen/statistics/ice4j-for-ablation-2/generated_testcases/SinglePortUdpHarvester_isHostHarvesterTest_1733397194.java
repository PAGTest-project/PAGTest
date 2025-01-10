
package org.ice4j.ice.harvest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SinglePortUdpHarvester_isHostHarvesterTest {

    @Test
    public void testIsHostHarvester() {
        SinglePortUdpHarvester harvester = new SinglePortUdpHarvester(null);
        assertTrue(harvester.isHostHarvester());
    }
}
