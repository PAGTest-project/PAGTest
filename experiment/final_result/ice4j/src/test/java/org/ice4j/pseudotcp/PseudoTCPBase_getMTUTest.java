
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PseudoTCPBase_getMTUTest {

    @Test
    public void testGetMTU() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.notifyMTU(1500);
        assertEquals(1500, pseudoTCPBase.getMTU());
    }
}
