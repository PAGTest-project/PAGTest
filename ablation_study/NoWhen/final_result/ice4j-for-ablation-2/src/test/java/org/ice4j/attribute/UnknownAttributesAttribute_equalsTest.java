
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
        att.addAttributeID((char) 1);
        assertFalse(unknownAttributesAttribute.equals(att));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        UnknownAttributesAttribute att = new UnknownAttributesAttribute();
        att.addAttributeID((char) 1);
        att.addAttributeID((char) 2);
        assertFalse(unknownAttributesAttribute.equals(att));
    }

    @Test
    public void testEquals_DifferentUnknownAttributes() {
        UnknownAttributesAttribute att = new UnknownAttributesAttribute();
        att.addAttributeID((char) 1);
        att.addAttributeID((char) 2);
        unknownAttributesAttribute.addAttributeID((char) 1);
        assertFalse(unknownAttributesAttribute.equals(att));
    }

    @Test
    public void testEquals_SameAttributes() {
        UnknownAttributesAttribute att = new UnknownAttributesAttribute();
        att.addAttributeID((char) 1);
        att.addAttributeID((char) 2);
        unknownAttributesAttribute.addAttributeID((char) 1);
        unknownAttributesAttribute.addAttributeID((char) 2);
        assertTrue(unknownAttributesAttribute.equals(att));
    }
}
