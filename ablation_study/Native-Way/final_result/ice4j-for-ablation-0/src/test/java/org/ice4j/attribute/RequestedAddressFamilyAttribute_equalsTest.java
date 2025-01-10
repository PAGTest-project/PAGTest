
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
        assertTrue(requestedAddressFamilyAttribute.equals(requestedAddressFamilyAttribute),
                "An object should be equal to itself.");
    }

    @Test
    public void testEqualsDifferentClass() {
        Object obj = new Object();
        assertFalse(requestedAddressFamilyAttribute.equals(obj),
                "An object of a different class should not be equal.");
    }

    @Test
    public void testEqualsDifferentAttributeType() {
        RequestedAddressFamilyAttribute differentTypeAttribute = new RequestedAddressFamilyAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        assertFalse(requestedAddressFamilyAttribute.equals(differentTypeAttribute),
                "Attributes with different types should not be equal.");
    }

    @Test
    public void testEqualsDifferentDataLength() {
        RequestedAddressFamilyAttribute differentLengthAttribute = new RequestedAddressFamilyAttribute() {
            @Override
            public char getDataLength() {
                return (char) (super.getDataLength() + 1);
            }
        };
        assertFalse(requestedAddressFamilyAttribute.equals(differentLengthAttribute),
                "Attributes with different data lengths should not be equal.");
    }

    @Test
    public void testEqualsDifferentFamily() {
        RequestedAddressFamilyAttribute differentFamilyAttribute = new RequestedAddressFamilyAttribute();
        differentFamilyAttribute.setFamily(RequestedAddressFamilyAttribute.IPv6);
        assertFalse(requestedAddressFamilyAttribute.equals(differentFamilyAttribute),
                "Attributes with different families should not be equal.");
    }

    @Test
    public void testEqualsSameAttributes() {
        RequestedAddressFamilyAttribute sameAttribute = new RequestedAddressFamilyAttribute();
        assertTrue(requestedAddressFamilyAttribute.equals(sameAttribute),
                "Attributes with the same values should be equal.");
    }
}
