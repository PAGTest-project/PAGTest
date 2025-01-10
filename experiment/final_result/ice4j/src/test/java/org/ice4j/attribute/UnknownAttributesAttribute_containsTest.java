
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_containsTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testContains_AttributePresent() {
        char attributeID = (char) 20;
        unknownAttributesAttribute.addAttributeID(attributeID);
        assertTrue(unknownAttributesAttribute.contains(attributeID), "Attribute should be present");
    }

    @Test
    public void testContains_AttributeNotPresent() {
        char attributeID = (char) 20;
        assertFalse(unknownAttributesAttribute.contains(attributeID), "Attribute should not be present");
    }
}
