
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SoftwareAttribute_equalsTest {
    private SoftwareAttribute softwareAttribute1;
    private SoftwareAttribute softwareAttribute2;
    private String softwareValue;

    @BeforeEach
    public void setUp() {
        softwareValue = "TestSoftware";
        softwareAttribute1 = new SoftwareAttribute();
        softwareAttribute1.setSoftware(softwareValue.getBytes());
        softwareAttribute2 = new SoftwareAttribute();
        softwareAttribute2.setSoftware(softwareValue.getBytes());
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(softwareAttribute1.equals(softwareAttribute1));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(softwareAttribute1.equals("NotASoftwareAttribute"));
    }

    @Test
    public void testEquals_DifferentSoftware() {
        softwareAttribute2.setSoftware("DifferentSoftware".getBytes());
        assertFalse(softwareAttribute1.equals(softwareAttribute2));
    }

    @Test
    public void testEquals_DifferentLength() {
        softwareAttribute2.setSoftware("Test".getBytes());
        assertFalse(softwareAttribute1.equals(softwareAttribute2));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        softwareAttribute2 = new SoftwareAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        softwareAttribute2.setSoftware(softwareValue.getBytes());
        assertFalse(softwareAttribute1.equals(softwareAttribute2));
    }

    @Test
    public void testEquals_EqualAttributes() {
        assertTrue(softwareAttribute1.equals(softwareAttribute2));
    }
}
