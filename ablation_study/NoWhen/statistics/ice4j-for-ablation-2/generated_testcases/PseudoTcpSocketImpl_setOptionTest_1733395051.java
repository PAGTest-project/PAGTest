
package org.ice4j.pseudotcp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PseudoTcpSocketImpl_setOptionTest {

    private PseudoTcpSocketImpl pseudoTcpSocketImpl;
    private Map<Integer, Object> options;

    @BeforeEach
    public void setUp() {
        pseudoTcpSocketImpl = new PseudoTcpSocketImpl(12345);
        options = new HashMap<>();
        pseudoTcpSocketImpl.options = options;
    }

    @Test
    public void testSetOption() throws SocketException {
        int optID = 1;
        Object value = "testValue";

        pseudoTcpSocketImpl.setOption(optID, value);

        assertEquals(value, options.get(optID));
    }
}
