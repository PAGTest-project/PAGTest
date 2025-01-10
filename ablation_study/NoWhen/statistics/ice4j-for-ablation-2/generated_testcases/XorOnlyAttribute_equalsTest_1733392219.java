
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class XorOnlyAttribute_equalsTest {
    private XorOnlyAttribute xorOnly;

    @BeforeEach
    public void setUp() {
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
        XorOnlyAttribute differentType = new XorOnlyAttribute() {
            @Override
            public char getAttributeType() {
                return Attribute.OTHER_TYPE;
            }
        };
        assertFalse(xorOnly.equals(differentType));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        XorOnlyAttribute differentLength = new XorOnlyAttribute() {
            @Override
            public char getDataLength() {
                return 1;
            }
        };
        assertFalse(xorOnly.equals(differentLength));
    }

    @Test
    public void testEquals_SameAttributes() {
        XorOnlyAttribute sameAttributes = new XorOnlyAttribute();
        assertTrue(xorOnly.equals(sameAttributes));
    }
}
