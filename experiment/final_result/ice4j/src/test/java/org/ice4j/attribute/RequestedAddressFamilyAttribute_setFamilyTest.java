
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_setFamilyTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;

    @BeforeEach
    public void setUp() {
        this.requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
    }

    @Test
    public void testSetFamilyValidIPv4() {
        assertTrue(requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv4));
        assertEquals(RequestedAddressFamilyAttribute.IPv4, requestedAddressFamilyAttribute.getFamily());
    }

    @Test
    public void testSetFamilyValidIPv6() {
        assertTrue(requestedAddressFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv6));
        assertEquals(RequestedAddressFamilyAttribute.IPv6, requestedAddressFamilyAttribute.getFamily());
    }

    @Test
    public void testSetFamilyInvalid() {
        assertFalse(requestedAddressFamilyAttribute.setFamily((char) 0x03));
        assertEquals(RequestedAddressFamilyAttribute.IPv4, requestedAddressFamilyAttribute.getFamily()); // Default value
    }
}
