
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_getDataLengthTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;
    private MsgFixture msgFixture;

    @BeforeEach
    public void setUp() throws Exception {
        this.requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
        this.msgFixture = new MsgFixture();
    }

    @Test
    public void testGetDataLength() {
        assertEquals(RequestedAddressFamilyAttribute.DATA_LENGTH, requestedAddressFamilyAttribute.getDataLength(), "Data length should be 1.");
    }

    @Test
    public void testEqualsWithSameDataLength() {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        assertTrue(requestedAddressFamilyAttribute.equals(other), "Attributes with the same data length should be equal.");
    }

    @Test
    public void testEqualsWithDifferentDataLength() {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute() {
            @Override
            public char getDataLength() {
                return 2;
            }
        };
        assertFalse(requestedAddressFamilyAttribute.equals(other), "Attributes with different data lengths should not be equal.");
    }

    @Test
    public void testEncodeDataLength() {
        byte[] encoded = requestedAddressFamilyAttribute.encode();
        assertEquals(RequestedAddressFamilyAttribute.DATA_LENGTH, (char) (encoded[2] << 8 | encoded[3]), "Encoded data length should match DATA_LENGTH.");
    }

    @Test
    public void testDecodeAttributeBodyV6() throws StunException {
        byte[] attributeValue = msgFixture.requestedAddressFamilyV6;
        char offset = Attribute.HEADER_LENGTH;
        char length = (char) (attributeValue.length - offset);

        requestedAddressFamilyAttribute.decodeAttributeBody(attributeValue, offset, length);

        assertEquals(MsgFixture.REQUESTED_ADDRESS_FAMILY_ATTRIBUTE_V6, requestedAddressFamilyAttribute.getFamily(), "RequestedAddressFamilyAttribute.decode() did not properly decode.");
    }
}
