
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_getFamilyTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        this.requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
    }

    @Test
    public void testGetFamily() {
        // Given
        char expectedFamily = RequestedAddressFamilyAttribute.IPv4;
        requestedAddressFamilyAttribute.setFamily(expectedFamily);

        // When
        char actualFamily = requestedAddressFamilyAttribute.getFamily();

        // Then
        assertEquals(expectedFamily, actualFamily, "The family value should match the expected value.");
    }
}
