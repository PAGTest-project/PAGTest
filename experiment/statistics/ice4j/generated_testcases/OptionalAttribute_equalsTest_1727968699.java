
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class OptionalAttribute_equalsTest {
    private OptionalAttribute optionalAttribute;
    private byte[] expectedAttributeValue;

    @BeforeEach
    public void setUp() throws Exception {
        MsgFixture msgFixture = new MsgFixture();
        int offset = Attribute.HEADER_LENGTH;

        // Initialize a sample body
        expectedAttributeValue = new byte[msgFixture.unknownOptionalAttribute.length - offset];
        System.arraycopy(msgFixture.unknownOptionalAttribute, offset, expectedAttributeValue, 0, expectedAttributeValue.length);

        optionalAttribute = new OptionalAttribute(msgFixture.optionalAttributeType);
        optionalAttribute.setBody(expectedAttributeValue, 0, expectedAttributeValue.length);
    }

    @Test
    public void testEquals_SameInstance() {
        assertTrue(optionalAttribute.equals(optionalAttribute));
    }

    @Test
    public void testEquals_DifferentInstanceSameValue() {
        OptionalAttribute anotherAttribute = new OptionalAttribute(optionalAttribute.getAttributeType());
        anotherAttribute.setBody(expectedAttributeValue, 0, expectedAttributeValue.length);
        assertTrue(optionalAttribute.equals(anotherAttribute));
    }

    @Test
    public void testEquals_DifferentInstanceDifferentValue() {
        byte[] differentValue = new byte[] {1, 2, 3};
        OptionalAttribute anotherAttribute = new OptionalAttribute(optionalAttribute.getAttributeType());
        anotherAttribute.setBody(differentValue, 0, differentValue.length);
        assertFalse(optionalAttribute.equals(anotherAttribute));
    }

    @Test
    public void testEquals_DifferentType() {
        Object nonOptionalAttribute = new Object();
        assertFalse(optionalAttribute.equals(nonOptionalAttribute));
    }
}
