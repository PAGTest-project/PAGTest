
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SoftwareAttribute_getNameTest {
    private SoftwareAttribute softwareAttribute;

    @BeforeEach
    public void setUp() {
        softwareAttribute = new SoftwareAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("SOFTWARE", softwareAttribute.getName());
    }
}
