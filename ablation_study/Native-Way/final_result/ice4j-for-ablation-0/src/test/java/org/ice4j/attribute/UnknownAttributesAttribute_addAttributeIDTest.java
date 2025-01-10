
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_addAttributeIDTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testAddAttributeIDNew() {
        char attributeID = 'A';
        unknownAttributesAttribute.addAttributeID(attributeID);
        assertTrue(unknownAttributesAttribute.contains(attributeID));
    }

    @Test
    public void testAddAttributeIDExisting() {
        char attributeID = 'B';
        unknownAttributesAttribute.addAttributeID(attributeID);
        unknownAttributesAttribute.addAttributeID(attributeID);
        assertEquals(1, unknownAttributesAttribute.getAttributeCount());
    }

    @Test
    public void testAddAttributeIDMultiple() {
        char attributeID1 = 'C';
        char attributeID2 = 'D';
        unknownAttributesAttribute.addAttributeID(attributeID1);
        unknownAttributesAttribute.addAttributeID(attributeID2);
        assertTrue(unknownAttributesAttribute.contains(attributeID1));
        assertTrue(unknownAttributesAttribute.contains(attributeID2));
        assertEquals(2, unknownAttributesAttribute.getAttributeCount());
    }
}
