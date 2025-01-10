
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SoftwareAttribute_equalsTest {
    private SoftwareAttribute softwareAttribute1;
    private SoftwareAttribute softwareAttribute2;

    @BeforeEach
    public void setUp() {
        softwareAttribute1 = new SoftwareAttribute();
        softwareAttribute2 = new SoftwareAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(softwareAttribute1.equals(softwareAttribute1));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(softwareAttribute1.equals("Not a SoftwareAttribute"));
    }

    @Test
    public void testEquals_DifferentSoftware() {
        softwareAttribute1.setSoftware("software1".getBytes());
        softwareAttribute2.setSoftware("software2".getBytes());
        assertFalse(softwareAttribute1.equals(softwareAttribute2));
    }

    @Test
    public void testEquals_SameSoftware() {
        softwareAttribute1.setSoftware("software".getBytes());
        softwareAttribute2.setSoftware("software".getBytes());
        assertTrue(softwareAttribute1.equals(softwareAttribute2));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        softwareAttribute1.setSoftware("software".getBytes());
        softwareAttribute2.setSoftware("software".getBytes());
        // Mocking different attribute types
        softwareAttribute1.setAttributeType(1);
        softwareAttribute2.setAttributeType(2);
        assertFalse(softwareAttribute1.equals(softwareAttribute2));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        softwareAttribute1.setSoftware("software".getBytes());
        softwareAttribute2.setSoftware("software1".getBytes());
        assertFalse(softwareAttribute1.equals(softwareAttribute2));
    }
}
