
package org.ice4j.attribute;

import org.ice4j.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ConnectionIdAttribute_getNameTest {
    private ConnectionIdAttribute connectionIdAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        this.connectionIdAttribute = new ConnectionIdAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("CONNECTION-ID", connectionIdAttribute.getName());
    }
}
