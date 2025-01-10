
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PseudoTCPBase_getM_rwnd_scaleTest {

    @Test
    public void testGetM_rwnd_scale() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 12345L);
        short expectedScale = 0;
        assertEquals(expectedScale, pseudoTCPBase.getM_rwnd_scale());
    }
}
