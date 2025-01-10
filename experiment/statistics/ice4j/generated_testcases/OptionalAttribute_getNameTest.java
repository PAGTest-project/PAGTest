
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.ice4j.MsgFixture;

public class OptionalAttribute_getNameTest {
    private OptionalAttribute optionalAttribute;
    private byte[] expectedAttributeValue;

    @BeforeEach
    public void setUp() throws Exception {
        MsgFixture msgFixture = new MsgFixture();
        int offset = Attribute.HEADER_LENGTH;

        // Initialize a sample body
        expectedAttributeValue =
            new byte[msgFixture.unknownOptionalAttribute.length - offset];

        System.arraycopy(msgFixture.unknownOptionalAttribute, offset,
                         expectedAttributeValue, 0,
                         expectedAttributeValue.length);

        optionalAttribute = new OptionalAttribute(
                                        msgFixture.optionalAttributeType);
    }

    @Test
    public void testGetName() {
        String expectedName = "Unknown Attribute";
        String actualName = optionalAttribute.getName();
        assertEquals(expectedName, actualName);
    }
}
