
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
        assertEquals(unknownAttributesAttribute, unknownAttributesAttribute);
    }

    @Test
    public void testEquals_DifferentClass() {
        Object obj = new Object();
        assertNotEquals(unknownAttributesAttribute, obj);
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        UnknownAttributesAttribute target = new UnknownAttributesAttribute();
        target.addAttributeID((char) 25);
        unknownAttributesAttribute.addAttributeID((char) 26);
        assertNotEquals(unknownAttributesAttribute, target);
    }

    @Test
    public void testEquals_DifferentDataLength() {
        UnknownAttributesAttribute target = new UnknownAttributesAttribute();
        target.addAttributeID((char) 25);
        unknownAttributesAttribute.addAttributeID((char) 25);
        unknownAttributesAttribute.addAttributeID((char) 26);
        assertNotEquals(unknownAttributesAttribute, target);
    }

    @Test
    public void testEquals_DifferentUnknownAttributes() {
        UnknownAttributesAttribute target = new UnknownAttributesAttribute();
        target.addAttributeID((char) 25);
        unknownAttributesAttribute.addAttributeID((char) 25);
        unknownAttributesAttribute.addAttributeID((char) 26);
        assertNotEquals(unknownAttributesAttribute, target);
    }

    @Test
    public void testEquals_SameAttributes() {
        UnknownAttributesAttribute target = new UnknownAttributesAttribute();
        target.addAttributeID((char) 25);
        target.addAttributeID((char) 26);
        unknownAttributesAttribute.addAttributeID((char) 25);
        unknownAttributesAttribute.addAttributeID((char) 26);
        assertEquals(unknownAttributesAttribute, target);
    }
}
