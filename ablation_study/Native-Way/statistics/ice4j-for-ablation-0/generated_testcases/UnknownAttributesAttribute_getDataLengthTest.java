
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class UnknownAttributesAttribute_getDataLengthTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testGetDataLengthEvenAttributes() {
        unknownAttributesAttribute.addAttributeID((char) 0x0001);
        unknownAttributesAttribute.addAttributeID((char) 0x0002);

        char expectedReturn = (char) (unknownAttributesAttribute.getAttributeCount() * 2);
        char actualReturn = unknownAttributesAttribute.getDataLength();
        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testGetDataLengthOddAttributes() {
        unknownAttributesAttribute.addAttributeID((char) 0x0001);
        unknownAttributesAttribute.addAttributeID((char) 0x0002);
        unknownAttributesAttribute.addAttributeID((char) 0x0003);

        char expectedReturn = (char) ((unknownAttributesAttribute.getAttributeCount() + 1) * 2);
        char actualReturn = unknownAttributesAttribute.getDataLength();
        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testGetDataLengthNoAttributes() {
        char expectedReturn = 0;
        char actualReturn = unknownAttributesAttribute.getDataLength();
        assertEquals(expectedReturn, actualReturn);
    }
}
