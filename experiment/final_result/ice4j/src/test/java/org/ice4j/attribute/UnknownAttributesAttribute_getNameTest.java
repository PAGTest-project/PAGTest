
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_getNameTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("UNKNOWN-ATTRIBUTES", unknownAttributesAttribute.getName());
    }
}
