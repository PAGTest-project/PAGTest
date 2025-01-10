
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PseudoTCPBase_getM_swnd_scaleTest {

    @Test
    public void testGetM_swnd_scale() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        pseudoTCPBase.applyWindowScaleOption((short) 5);
        assertEquals(5, pseudoTCPBase.getM_swnd_scale());
    }
}
