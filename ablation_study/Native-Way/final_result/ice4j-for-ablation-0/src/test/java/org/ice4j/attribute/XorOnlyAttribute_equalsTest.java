
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
        assertTrue(xorOnly.equals(xorOnly));
    }

    @Test
    public void testEquals_DifferentType() {
        Object obj = new Object();
        assertFalse(xorOnly.equals(obj));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        XorOnlyAttribute att = new XorOnlyAttribute() {
            @Override
            public char getAttributeType() {
                return 1; // Different attribute type
            }
        };
        assertFalse(xorOnly.equals(att));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        XorOnlyAttribute att = new XorOnlyAttribute() {
            @Override
            public char getDataLength() {
                return 1; // Different data length
            }
        };
        assertFalse(xorOnly.equals(att));
    }

    @Test
    public void testEquals_SameAttributeTypeAndDataLength() {
        XorOnlyAttribute att = new XorOnlyAttribute();
        assertTrue(xorOnly.equals(att));
    }
}
