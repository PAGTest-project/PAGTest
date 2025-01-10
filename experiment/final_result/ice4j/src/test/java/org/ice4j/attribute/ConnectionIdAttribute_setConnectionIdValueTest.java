
package org.ice4j.attribute;

import org.ice4j.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ConnectionIdAttribute_setConnectionIdValueTest {
    private ConnectionIdAttribute connectionIdAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        this.connectionIdAttribute = new ConnectionIdAttribute();
    }

    @Test
    public void testSetConnectionIdValue() {
        int connectionId = 123456;
        connectionIdAttribute.setConnectionIdValue(connectionId);
        assertEquals(connectionId, connectionIdAttribute.getConnectionIdValue(), "ConnectionIdValue was not set correctly.");
    }
}
