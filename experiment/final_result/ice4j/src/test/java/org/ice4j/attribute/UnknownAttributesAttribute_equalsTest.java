
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class UnknownAttributesAttribute_equalsTest {
    private UnknownAttributesAttribute unknownAttributesAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        unknownAttributesAttribute = new UnknownAttributesAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(unknownAttributesAttribute.equals(unknownAttributesAttribute));
    }

    @Test
    public void testEquals_DifferentType() {
        Object obj = new Object();
        assertFalse(unknownAttributesAttribute.equals(obj));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        UnknownAttributesAttribute att = new UnknownAttributesAttribute();
        att.addAttributeID((char) 0x0001);
        unknownAttributesAttribute.addAttributeID((char) 0x0002);
        assertFalse(unknownAttributesAttribute.equals(att));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        UnknownAttributesAttribute att = new UnknownAttributesAttribute();
        att.addAttributeID((char) 0x0001);
        unknownAttributesAttribute.addAttributeID((char) 0x0001);
        unknownAttributesAttribute.addAttributeID((char) 0x0002);
        assertFalse(unknownAttributesAttribute.equals(att));
    }

    @Test
    public void testEquals_DifferentUnknownAttributes() {
        UnknownAttributesAttribute att = new UnknownAttributesAttribute();
        att.addAttributeID((char) 0x0001);
        unknownAttributesAttribute.addAttributeID((char) 0x0002);
        assertFalse(unknownAttributesAttribute.equals(att));
    }

    @Test
    public void testEquals_SameAttributes() {
        UnknownAttributesAttribute att = new UnknownAttributesAttribute();
        att.addAttributeID((char) 0x0001);
        unknownAttributesAttribute.addAttributeID((char) 0x0001);
        assertTrue(unknownAttributesAttribute.equals(att));
    }
}
