
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
}
