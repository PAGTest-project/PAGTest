
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
        unknownAttributesAttribute.addAttributeID((char) 20);
        unknownAttributesAttribute.addAttributeID((char) 21);
        assertEquals(4, unknownAttributesAttribute.getDataLength());
    }

    @Test
    public void testGetDataLengthOddAttributes() {
        unknownAttributesAttribute.addAttributeID((char) 20);
        unknownAttributesAttribute.addAttributeID((char) 21);
        unknownAttributesAttribute.addAttributeID((char) 22);
        assertEquals(8, unknownAttributesAttribute.getDataLength());
    }

    @Test
    public void testGetDataLengthNoAttributes() {
        assertEquals(0, unknownAttributesAttribute.getDataLength());
    }

    @Test
    public void testGetDataLengthSingleAttribute() {
        unknownAttributesAttribute.addAttributeID((char) 20);
        assertEquals(4, unknownAttributesAttribute.getDataLength());
    }
}
