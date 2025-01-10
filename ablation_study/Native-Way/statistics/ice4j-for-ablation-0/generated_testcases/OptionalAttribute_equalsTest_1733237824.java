
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.ice4j.MsgFixture;

public class OptionalAttribute_equalsTest {
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
        optionalAttribute.setBody(expectedAttributeValue, 0,
                                  expectedAttributeValue.length);
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(optionalAttribute.equals(optionalAttribute));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(optionalAttribute.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentAttributeValue() {
        OptionalAttribute differentAttribute = new OptionalAttribute(
                                        msgFixture.optionalAttributeType);
        byte[] differentAttributeValue = new byte[expectedAttributeValue.length];
        for (int i = 0; i < differentAttributeValue.length; i++) {
            differentAttributeValue[i] = (byte)(expectedAttributeValue[i] + 1);
        }
        differentAttribute.setBody(differentAttributeValue, 0,
                                   differentAttributeValue.length);
        assertFalse(optionalAttribute.equals(differentAttribute));
    }

    @Test
    public void testEquals_SameAttributeValue() {
        OptionalAttribute sameAttribute = new OptionalAttribute(
                                        msgFixture.optionalAttributeType);
        sameAttribute.setBody(expectedAttributeValue, 0,
                              expectedAttributeValue.length);
        assertTrue(optionalAttribute.equals(sameAttribute));
    }
}
