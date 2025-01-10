
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SoftwareAttribute_equalsTest {
    private SoftwareAttribute softwareAttribute;
    private String softwareValue;

    @BeforeEach
    public void setUp() throws Exception {
        softwareValue = "TestSoftware";
        softwareAttribute = new SoftwareAttribute();
        softwareAttribute.setSoftware(softwareValue.getBytes());
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(softwareAttribute.equals(softwareAttribute));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(softwareAttribute.equals("NotASoftwareAttribute"));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        SoftwareAttribute differentTypeAttribute = new SoftwareAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        differentTypeAttribute.setSoftware(softwareValue.getBytes());
        assertFalse(softwareAttribute.equals(differentTypeAttribute));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        SoftwareAttribute differentLengthAttribute = new SoftwareAttribute();
        differentLengthAttribute.setSoftware("DifferentLength".getBytes());
        assertFalse(softwareAttribute.equals(differentLengthAttribute));
    }

    @Test
    public void testEquals_DifferentSoftware() {
        SoftwareAttribute differentSoftwareAttribute = new SoftwareAttribute();
        differentSoftwareAttribute.setSoftware("DifferentSoftware".getBytes());
        assertFalse(softwareAttribute.equals(differentSoftwareAttribute));
    }

    @Test
    public void testEquals_EqualAttributes() {
        SoftwareAttribute equalAttribute = new SoftwareAttribute();
        equalAttribute.setSoftware(softwareValue.getBytes());
        assertTrue(softwareAttribute.equals(equalAttribute));
    }
}
