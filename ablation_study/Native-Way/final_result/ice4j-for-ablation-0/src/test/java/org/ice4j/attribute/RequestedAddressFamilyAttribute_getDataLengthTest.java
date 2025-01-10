
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_getDataLengthTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
    }

    @Test
    public void testGetDataLength() {
        char expectedReturn = RequestedAddressFamilyAttribute.DATA_LENGTH;
        char actualReturn = requestedAddressFamilyAttribute.getDataLength();
        assertEquals(expectedReturn, actualReturn);
    }
}
