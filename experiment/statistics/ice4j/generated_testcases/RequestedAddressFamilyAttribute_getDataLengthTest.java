
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_getDataLengthTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;

    @BeforeEach
    public void setUp() {
        this.requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
    }

    @Test
    public void testGetDataLength() {
        char expectedLength = 1;
        char actualLength = this.requestedAddressFamilyAttribute.getDataLength();
        assertEquals(expectedLength, actualLength, "Data length is not properly calculated");
    }
}
