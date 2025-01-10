
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_equalsTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;
    private MsgFixture msgFixture;

    @BeforeEach
    public void setUp() throws Exception {
        this.requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
        this.msgFixture = new MsgFixture();
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(requestedAddressFamilyAttribute.equals(requestedAddressFamilyAttribute));
    }

    @Test
    public void testEqualsDifferentType() {
        Object obj = new Object();
        assertFalse(requestedAddressFamilyAttribute.equals(obj));
    }

    @Test
    public void testEqualsDifferentFamily() throws StunException {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        other.setFamily(RequestedAddressFamilyAttribute.IPv6);
        assertFalse(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsSameFamily() throws StunException {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        assertTrue(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsDifferentAttributeType() throws StunException {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        assertFalse(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsDifferentDataLength() throws StunException {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute() {
            @Override
            public char getDataLength() {
                return (char) (super.getDataLength() + 1);
            }
        };
        assertFalse(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsAfterDecode() throws StunException {
        byte[] attributeValue = msgFixture.requestedAddressFamilyV6;
        char offset = Attribute.HEADER_LENGTH;
        char length = (char) (attributeValue.length - offset);

        RequestedAddressFamilyAttribute decodedAttribute = new RequestedAddressFamilyAttribute();
        decodedAttribute.decodeAttributeBody(attributeValue, offset, length);

        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        other.setFamily(RequestedAddressFamilyAttribute.IPv6);

        assertTrue(decodedAttribute.equals(other));
    }
}
