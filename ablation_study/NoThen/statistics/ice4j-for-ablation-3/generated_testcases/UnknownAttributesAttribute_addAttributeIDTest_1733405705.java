
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_addAttributeIDTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;
    private MsgFixture binMessagesFixture;

    @BeforeEach
    public void setUp() throws Exception {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
        binMessagesFixture = new MsgFixture();
    }

    @Test
    public void testAddAttributeID_NewAttribute() {
        char attributeID = 'A';
        assertFalse(unknownAttributesAttribute.contains(attributeID));

        unknownAttributesAttribute.addAttributeID(attributeID);
        assertTrue(unknownAttributesAttribute.contains(attributeID));
        assertEquals(1, unknownAttributesAttribute.getAttributeCount());
    }

    @Test
    public void testAddAttributeID_DuplicateAttribute() {
        char attributeID = 'B';
        unknownAttributesAttribute.addAttributeID(attributeID);
        assertTrue(unknownAttributesAttribute.contains(attributeID));
        assertEquals(1, unknownAttributesAttribute.getAttributeCount());

        unknownAttributesAttribute.addAttributeID(attributeID);
        assertTrue(unknownAttributesAttribute.contains(attributeID));
        assertEquals(1, unknownAttributesAttribute.getAttributeCount());
    }

    @Test
    public void testAddAttributeID_EncodeUpdated() {
        char attributeID1 = 'C';
        char attributeID2 = 'D';
        unknownAttributesAttribute.addAttributeID(attributeID1);
        unknownAttributesAttribute.addAttributeID(attributeID2);

        byte[] expectedReturn = binMessagesFixture.unknownAttsEncodeExpectedResult;
        byte[] actualReturn = unknownAttributesAttribute.encode();
        assertArrayEquals(actualReturn, expectedReturn);
    }
}
