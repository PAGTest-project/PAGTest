
package org.ice4j.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionIdAttribute_getNameTest {
    private ConnectionIdAttribute connectionIdAttribute;

    @BeforeEach
    public void setUp() {
        connectionIdAttribute = new ConnectionIdAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("CONNECTION-ID", connectionIdAttribute.getName());
    }
}
