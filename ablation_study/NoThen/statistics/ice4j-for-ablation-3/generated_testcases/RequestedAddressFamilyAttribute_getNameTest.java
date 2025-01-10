
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class RequestedAddressFamilyAttribute_getNameTest {
    private RequestedAddressFamilyAttribute requestedAddressFamilyAttribute;
    private MsgFixture msgFixture;

    @BeforeEach
    public void setUp() throws Exception {
        this.requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
        this.msgFixture = new MsgFixture();
    }

    @Test
    public void testGetName() {
        String expectedName = "REQUESTED-ADDRESS-FAMILY";
        String actualName = requestedAddressFamilyAttribute.getName();
        assertEquals(expectedName, actualName, "The getName() method did not return the expected attribute name.");
    }

    @Test
    public void testGetNameAfterSetFamily() {
        String expectedName = "REQUESTED-ADDRESS-FAMILY";
        requestedAddressFamilyAttribute.setFamily(MsgFixture.REQUESTED_ADDRESS_FAMILY_ATTRIBUTE_V4);
        String actualName = requestedAddressFamilyAttribute.getName();
        assertEquals(expectedName, actualName, "The getName() method did not return the expected attribute name after setting the family.");
    }
}
