
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SoftwareAttribute_getDataLengthTest {
    private SoftwareAttribute softwareAttribute;
    private byte[] softwareValue;

    @BeforeEach
    public void setUp() {
        softwareAttribute = new SoftwareAttribute();
        softwareValue = "testSoftware".getBytes();
        softwareAttribute.setSoftware(softwareValue);
    }

    @Test
    public void testGetDataLength() {
        assertEquals(softwareValue.length, softwareAttribute.getDataLength());
    }
}
