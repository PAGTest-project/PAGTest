
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_getAttributeTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testGetAttribute() {
        // Given
        unknownAttributesAttribute.addAttributeID((char) 20);
        unknownAttributesAttribute.addAttributeID((char) 21);
        unknownAttributesAttribute.addAttributeID((char) 22);

        // When
        char actualReturn = unknownAttributesAttribute.getAttribute(1);

        // Then
        assertEquals((char) 21, actualReturn, "Incorrect getAttribute() return value");
    }
}
