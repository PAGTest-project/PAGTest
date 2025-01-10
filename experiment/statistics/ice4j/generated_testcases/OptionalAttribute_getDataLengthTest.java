
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class OptionalAttribute_getDataLengthTest {
    private OptionalAttribute optionalAttribute;
    private byte[] expectedAttributeValue;

    @BeforeEach
    public void setUp() throws Exception {
        optionalAttribute = new OptionalAttribute((char) 0x0001);
        expectedAttributeValue = new byte[] { 0x01, 0x02, 0x03, 0x04 };
        optionalAttribute.setBody(expectedAttributeValue, 0, expectedAttributeValue.length);
    }

    @Test
    public void testGetDataLength() {
        char expectedLength = (char) expectedAttributeValue.length;
        assertEquals(expectedLength, optionalAttribute.getDataLength());
    }
}
