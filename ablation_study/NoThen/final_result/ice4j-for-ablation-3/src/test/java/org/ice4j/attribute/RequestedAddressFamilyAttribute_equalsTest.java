
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
    public void testEqualsDifferentFamily() {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        other.setFamily(RequestedAddressFamilyAttribute.IPv6);
        assertFalse(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsSameFamily() {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        assertTrue(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsDifferentDataLength() {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute() {
            @Override
            public char getDataLength() {
                return 2;
            }
        };
        assertFalse(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsDifferentAttributeType() {
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute() {
            @Override
            public char getAttributeType() {
                return 0x0002;
            }
        };
        assertFalse(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsAfterDecode() throws StunException {
        byte[] attributeValue = new byte[]{(byte) RequestedAddressFamilyAttribute.IPv6};
        requestedAddressFamilyAttribute.decodeAttributeBody(attributeValue, (char) 0, (char) 1);
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        other.setFamily(RequestedAddressFamilyAttribute.IPv6);
        assertTrue(requestedAddressFamilyAttribute.equals(other));
    }
}
