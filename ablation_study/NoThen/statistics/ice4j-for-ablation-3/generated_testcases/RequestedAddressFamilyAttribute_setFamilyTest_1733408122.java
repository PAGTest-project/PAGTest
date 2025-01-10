
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_setFamilyTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;
    private MsgFixture msgFixture;

    @BeforeEach
    public void setUp() throws Exception {
        this.requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
        this.msgFixture = new MsgFixture();
    }

    @Test
    public void testSetFamilyIPv4() {
        assertTrue(requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv4));
        assertEquals(RequestedAddressFamilyAttribute.IPv4, requestedAddressFamilyAttribute.getFamily());
    }

    @Test
    public void testSetFamilyIPv6() {
        assertTrue(requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv6));
        assertEquals(RequestedAddressFamilyAttribute.IPv6, requestedAddressFamilyAttribute.getFamily());
    }

    @Test
    public void testSetFamilyInvalid() {
        assertFalse(requestedAddressFamilyAttribute.setFamily((char) 0x03));
        assertEquals(RequestedAddressFamilyAttribute.IPv4, requestedAddressFamilyAttribute.getFamily());
    }

    @Test
    public void testEncodeAfterSetFamilyIPv4() {
        requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv4);
        byte[] expectedReturn = msgFixture.requestedAddressFamilyV4;
        byte[] actualReturn = requestedAddressFamilyAttribute.encode();
        assertArrayEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testEncodeAfterSetFamilyIPv6() {
        requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv6);
        byte[] expectedReturn = msgFixture.requestedAddressFamilyV6;
        byte[] actualReturn = requestedAddressFamilyAttribute.encode();
        assertArrayEquals(expectedReturn, actualReturn);
    }

    @Test
    public void testEqualsAfterSetFamilyIPv4() {
        requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv4);
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        other.setFamily(RequestedAddressFamilyAttribute.IPv4);
        assertTrue(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsAfterSetFamilyIPv6() {
        requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv6);
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        other.setFamily(RequestedAddressFamilyAttribute.IPv6);
        assertTrue(requestedAddressFamilyAttribute.equals(other));
    }

    @Test
    public void testEqualsAfterSetFamilyInvalid() {
        requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv4);
        RequestedAddressFamilyAttribute other = new RequestedAddressFamilyAttribute();
        other.setFamily((char) 0x03);
        assertFalse(requestedAddressFamilyAttribute.equals(other));
    }
}
