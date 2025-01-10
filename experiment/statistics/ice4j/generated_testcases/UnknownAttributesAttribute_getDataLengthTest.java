
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_getDataLengthTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testGetDataLengthEvenAttributes() {
        unknownAttributesAttribute.addAttributeID((char) 1);
        unknownAttributesAttribute.addAttributeID((char) 2);
        assertEquals(4, unknownAttributesAttribute.getDataLength());
    }

    @Test
    public void testGetDataLengthOddAttributes() {
        unknownAttributesAttribute.addAttributeID((char) 1);
        unknownAttributesAttribute.addAttributeID((char) 2);
        unknownAttributesAttribute.addAttributeID((char) 3);
        assertEquals(8, unknownAttributesAttribute.getDataLength());
    }
}
