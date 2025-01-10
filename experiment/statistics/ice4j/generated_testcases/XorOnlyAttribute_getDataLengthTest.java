
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class XorOnlyAttribute_getDataLengthTest {
    private XorOnlyAttribute xorOnly;

    @BeforeEach
    public void setUp() {
        xorOnly = new XorOnlyAttribute();
    }

    @Test
    public void testGetDataLength() {
        assertEquals(0, xorOnly.getDataLength());
    }
}
