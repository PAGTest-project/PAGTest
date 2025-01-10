
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PseudoTCPBase_getAckNotifyTest {

    @Test
    public void testGetAckNotify() {
        PseudoTCPBase pseudoTCPBase = new PseudoTCPBase(null, 0);
        assertNotNull(pseudoTCPBase.getAckNotify());
    }
}
