
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PseudoTCPBase_nowTest {

    @Test
    public void testNow() {
        long result = PseudoTCPBase.now();
        assertTrue(result >= 0);
    }
}
