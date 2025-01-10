
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
        assertTrue(unknownAttributesAttribute.contains(attributeID), "Attribute ID should be added");
        assertEquals(1, unknownAttributesAttribute.getAttributeCount(), "Attribute count should be 1");
    }

    @Test
    public void testAddAttributeID_DuplicateAttribute() {
        char attributeID = 'B';
        unknownAttributesAttribute.addAttributeID(attributeID);
        unknownAttributesAttribute.addAttributeID(attributeID); // Duplicate
        assertTrue(unknownAttributesAttribute.contains(attributeID), "Attribute ID should be added");
        assertEquals(1, unknownAttributesAttribute.getAttributeCount(), "Attribute count should still be 1");
    }

    @Test
    public void testAddAttributeID_MultipleAttributes() {
        char attributeID1 = 'C';
        char attributeID2 = 'D';
        unknownAttributesAttribute.addAttributeID(attributeID1);
        unknownAttributesAttribute.addAttributeID(attributeID2);
        assertTrue(unknownAttributesAttribute.contains(attributeID1), "Attribute ID1 should be added");
        assertTrue(unknownAttributesAttribute.contains(attributeID2), "Attribute ID2 should be added");
        assertEquals(2, unknownAttributesAttribute.getAttributeCount(), "Attribute count should be 2");
    }
}
