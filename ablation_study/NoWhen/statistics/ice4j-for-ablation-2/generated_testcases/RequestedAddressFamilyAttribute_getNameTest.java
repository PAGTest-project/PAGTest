
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_getNameTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        this.requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("REQUESTED-ADDRESS-FAMILY", requestedAddressFamilyAttribute.getName());
    }
}
