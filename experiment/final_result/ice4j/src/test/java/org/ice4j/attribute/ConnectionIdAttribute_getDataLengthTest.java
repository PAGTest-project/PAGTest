
package org.ice4j.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionIdAttribute_getDataLengthTest {
    private ConnectionIdAttribute connectionIdAttribute;

    @BeforeEach
    public void setUp() {
        connectionIdAttribute = new ConnectionIdAttribute();
    }

    @Test
    public void testGetDataLength() {
        assertEquals(4, connectionIdAttribute.getDataLength(), "Data length should be 4 bytes.");
    }
}
