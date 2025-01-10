
package org.ice4j.ice.harvest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class SinglePortUdpHarvester_isHostHarvesterTest {

    @Test
    public void testIsHostHarvester() {
        SinglePortUdpHarvester harvester = null;
        try {
            harvester = new SinglePortUdpHarvester(null);
        } catch (IOException e) {
            fail("Failed to create SinglePortUdpHarvester: " + e.getMessage());
        }
        assertTrue(harvester.isHostHarvester());
    }
}
