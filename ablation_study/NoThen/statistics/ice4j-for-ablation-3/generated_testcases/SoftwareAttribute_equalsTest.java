
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SoftwareAttribute_equalsTest {
    private SoftwareAttribute softwareAttribute;
    private String softwareValue;

    @BeforeEach
    public void setUp() throws Exception {
        softwareAttribute = new SoftwareAttribute();
        softwareValue = "testSoftware";
        softwareAttribute.setSoftware(softwareValue.getBytes());
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(softwareAttribute.equals(softwareAttribute));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(softwareAttribute.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentSoftware() {
        SoftwareAttribute differentSoftwareAttribute = new SoftwareAttribute();
        differentSoftwareAttribute.setSoftware("differentSoftware".getBytes());
        assertFalse(softwareAttribute.equals(differentSoftwareAttribute));
    }

    @Test
    public void testEquals_SameSoftware() {
        SoftwareAttribute sameSoftwareAttribute = new SoftwareAttribute();
        sameSoftwareAttribute.setSoftware(softwareValue.getBytes());
        assertTrue(softwareAttribute.equals(sameSoftwareAttribute));
    }

    @Test
    public void testEquals_NullSoftware() {
        SoftwareAttribute nullSoftwareAttribute = new SoftwareAttribute();
        assertFalse(softwareAttribute.equals(nullSoftwareAttribute));
    }

    @Test
    public void testEquals_DifferentLength() {
        SoftwareAttribute differentLengthAttribute = new SoftwareAttribute();
        differentLengthAttribute.setSoftware("test".getBytes());
        assertFalse(softwareAttribute.equals(differentLengthAttribute));
    }
}
