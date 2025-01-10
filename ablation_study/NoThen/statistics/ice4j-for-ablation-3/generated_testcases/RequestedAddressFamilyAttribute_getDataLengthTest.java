
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
        assertEquals(RequestedAddressFamilyAttribute.DATA_LENGTH, requestedAddressFamilyAttribute.getDataLength(), "getDataLength() should return the constant DATA_LENGTH");
    }

    @Test
    public void testSetFamilyValidIPv4() {
        assertTrue(requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv4), "setFamily() should return true for valid IPv4 family");
        assertEquals(RequestedAddressFamilyAttribute.IPv4, requestedAddressFamilyAttribute.getFamily(), "getFamily() should return IPv4 after setting it");
    }

    @Test
    public void testSetFamilyValidIPv6() {
        assertTrue(requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv6), "setFamily() should return true for valid IPv6 family");
        assertEquals(RequestedAddressFamilyAttribute.IPv6, requestedAddressFamilyAttribute.getFamily(), "getFamily() should return IPv6 after setting it");
    }

    @Test
    public void testSetFamilyInvalid() {
        assertFalse(requestedAddressFamilyAttribute.setFamily((char) 0x03), "setFamily() should return false for invalid family");
    }

    @Test
    public void testDecodeAttributeBodyValidIPv4() throws StunException {
        byte[] attributeValue = new byte[]{(byte) RequestedAddressFamilyAttribute.IPv4};
        requestedAddressFamilyAttribute.decodeAttributeBody(attributeValue, (char) 0, (char) 1);
        assertEquals(RequestedAddressFamilyAttribute.IPv4, requestedAddressFamilyAttribute.getFamily(), "decodeAttributeBody() should set family to IPv4 for valid input");
    }

    @Test
    public void testDecodeAttributeBodyValidIPv6() throws StunException {
        byte[] attributeValue = new byte[]{(byte) RequestedAddressFamilyAttribute.IPv6};
        requestedAddressFamilyAttribute.decodeAttributeBody(attributeValue, (char) 0, (char) 1);
        assertEquals(RequestedAddressFamilyAttribute.IPv6, requestedAddressFamilyAttribute.getFamily(), "decodeAttributeBody() should set family to IPv6 for valid input");
    }

    @Test
    public void testDecodeAttributeBodyInvalidLength() {
        byte[] attributeValue = new byte[]{(byte) RequestedAddressFamilyAttribute.IPv4};
        assertThrows(StunException.class, () -> requestedAddressFamilyAttribute.decodeAttributeBody(attributeValue, (char) 0, (char) 2), "decodeAttributeBody() should throw StunException for invalid length");
    }

    @Test
    public void testDecodeAttributeBodyInvalidFamily() {
        byte[] attributeValue = new byte[]{(byte) 0x03};
        assertThrows(StunException.class, () -> requestedAddressFamilyAttribute.decodeAttributeBody(attributeValue, (char) 0, (char) 1), "decodeAttributeBody() should throw StunException for invalid family");
    }
}
