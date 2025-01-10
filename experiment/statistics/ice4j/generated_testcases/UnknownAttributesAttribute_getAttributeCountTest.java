
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_getAttributeCountTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testGetAttributeCount() {
        // Given
        unknownAttributesAttribute.addAttributeID((char) 20);
        unknownAttributesAttribute.addAttributeID((char) 21);
        unknownAttributesAttribute.addAttributeID((char) 22);

        // When
        int actualCount = unknownAttributesAttribute.getAttributeCount();

        // Then
        assertEquals(3, actualCount, "Incorrect getAttributeCount() return value");
    }
}
