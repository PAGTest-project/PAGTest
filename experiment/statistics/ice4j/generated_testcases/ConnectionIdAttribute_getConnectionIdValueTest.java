
package org.ice4j.attribute;

import org.ice4j.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ConnectionIdAttribute_getConnectionIdValueTest {
    private ConnectionIdAttribute connectionIdAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        this.connectionIdAttribute = new ConnectionIdAttribute();
    }

    @Test
    public void testGetConnectionIdValue() {
        int expectedConnectionIdValue = 123456;
        connectionIdAttribute.setConnectionIdValue(expectedConnectionIdValue);
        assertEquals(expectedConnectionIdValue, connectionIdAttribute.getConnectionIdValue(), "getConnectionIdValue() did not return the expected value.");
    }
}
