
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class OptionalAttribute_equalsTest {
    private OptionalAttribute optionalAttribute;
    private MsgFixture msgFixture;
    private byte[] expectedAttributeValue;

    @BeforeEach
    public void setUp() throws Exception {
        msgFixture = new MsgFixture();
        int offset = Attribute.HEADER_LENGTH;

        // Init a sample body
        expectedAttributeValue =
            new byte[msgFixture.unknownOptionalAttribute.length - offset];

        System.arraycopy(msgFixture.unknownOptionalAttribute, offset,
                         expectedAttributeValue, 0,
                         expectedAttributeValue.length);

        optionalAttribute = new OptionalAttribute(
                                        msgFixture.optionalAttributeType);
    }

    @Test
    public void testEqualsWithSameObject() {
        assertTrue(optionalAttribute.equals(optionalAttribute),
            "An object should be equal to itself.");
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(optionalAttribute.equals(null),
            "An object should not be equal to null.");
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Object obj = new Object();
        assertFalse(optionalAttribute.equals(obj),
            "An OptionalAttribute should not be equal to an object of a different class.");
    }

    @Test
    public void testEqualsWithDifferentAttributeValue() throws StunException {
        OptionalAttribute otherAttribute = new OptionalAttribute(
                                        msgFixture.optionalAttributeType);
        otherAttribute.decodeAttributeBody(msgFixture.unknownOptionalAttribute,
                                           Attribute.HEADER_LENGTH,
                                           (char)(msgFixture.unknownOptionalAttribute.length - Attribute.HEADER_LENGTH));

        // Modify the attributeValue of the otherAttribute
        byte[] modifiedAttributeValue = new byte[expectedAttributeValue.length];
        System.arraycopy(expectedAttributeValue, 0, modifiedAttributeValue, 0, expectedAttributeValue.length);
        modifiedAttributeValue[0] = (byte)(modifiedAttributeValue[0] + 1);
        otherAttribute.setBody(modifiedAttributeValue, 0, modifiedAttributeValue.length);

        assertFalse(optionalAttribute.equals(otherAttribute),
            "OptionalAttributes with different attributeValue should not be equal.");
    }

    @Test
    public void testEqualsWithSameAttributeValue() throws StunException {
        OptionalAttribute otherAttribute = new OptionalAttribute(
                                        msgFixture.optionalAttributeType);
        otherAttribute.decodeAttributeBody(msgFixture.unknownOptionalAttribute,
                                           Attribute.HEADER_LENGTH,
                                           (char)(msgFixture.unknownOptionalAttribute.length - Attribute.HEADER_LENGTH));

        assertTrue(optionalAttribute.equals(otherAttribute),
            "OptionalAttributes with the same attributeValue should be equal.");
    }
}
