
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_addAttributeIDTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testAddAttributeID_NewAttribute() {
        char attributeID = 'A';
        unknownAttributesAttribute.addAttributeID(attributeID);
        assertTrue(unknownAttributesAttribute.contains(attributeID));
        assertEquals(1, unknownAttributesAttribute.getAttributeCount());
    }

    @Test
    public void testAddAttributeID_DuplicateAttribute() {
        char attributeID = 'B';
        unknownAttributesAttribute.addAttributeID(attributeID);
        unknownAttributesAttribute.addAttributeID(attributeID);
        assertTrue(unknownAttributesAttribute.contains(attributeID));
        assertEquals(1, unknownAttributesAttribute.getAttributeCount());
    }
}
