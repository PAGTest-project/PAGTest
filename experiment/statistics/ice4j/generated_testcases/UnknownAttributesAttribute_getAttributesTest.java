
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_getAttributesTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testGetAttributes() {
        // Given
        char attributeID1 = 0x22;
        char attributeID2 = 0x33;
        unknownAttributesAttribute.addAttributeID(attributeID1);
        unknownAttributesAttribute.addAttributeID(attributeID2);

        // When
        Iterator<Character> attributes = unknownAttributesAttribute.getAttributes();

        // Then
        assertTrue(attributes.hasNext());
        assertEquals(attributeID1, attributes.next().charValue());
        assertTrue(attributes.hasNext());
        assertEquals(attributeID2, attributes.next().charValue());
        assertFalse(attributes.hasNext());
    }
}
