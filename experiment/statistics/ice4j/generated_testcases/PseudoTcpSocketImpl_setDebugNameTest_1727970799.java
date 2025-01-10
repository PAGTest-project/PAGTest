
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PseudoTcpSocketImpl_setDebugNameTest {

    @Test
    void testSetDebugName() {
        PseudoTcpSocketImpl socketImpl = new PseudoTcpSocketImpl(12345);
        socketImpl.setDebugName("TestDebugName");
        assertEquals("TestDebugName", socketImpl.pseudoTcp.debugName);
    }
}
