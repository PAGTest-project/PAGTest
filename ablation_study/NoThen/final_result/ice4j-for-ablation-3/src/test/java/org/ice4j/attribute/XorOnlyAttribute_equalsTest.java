
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class XorOnlyAttribute_equalsTest {
    private XorOnlyAttribute xorOnly;

    @BeforeEach
    public void setUp() throws Exception {
        xorOnly = new XorOnlyAttribute();
    }

    @Test
    public void testEquals_SameInstance() {
        assertTrue(xorOnly.equals(xorOnly), "An instance should be equal to itself");
    }

    @Test
    public void testEquals_DifferentType() {
        Object obj = new Object();
        assertFalse(xorOnly.equals(obj), "An instance should not be equal to an object of a different type");
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        XorOnlyAttribute differentType = new XorOnlyAttribute() {
            @Override
            public char getAttributeType() {
                return 123; // Different attribute type
            }
        };
        assertFalse(xorOnly.equals(differentType), "Instances with different attribute types should not be equal");
    }

    @Test
    public void testEquals_DifferentDataLength() {
        XorOnlyAttribute differentLength = new XorOnlyAttribute() {
            @Override
            public char getDataLength() {
                return 1; // Different data length
            }
        };
        assertFalse(xorOnly.equals(differentLength), "Instances with different data lengths should not be equal");
    }

    @Test
    public void testEquals_SameAttributes() {
        XorOnlyAttribute sameAttributes = new XorOnlyAttribute();
        assertTrue(xorOnly.equals(sameAttributes), "Instances with the same attributes should be equal");
    }
}
