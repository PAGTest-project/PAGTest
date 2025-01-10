
package org.ice4j.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeRequestAttribute_getDataLengthTest {
    private ChangeRequestAttribute changeRequestAttribute;

    @BeforeEach
    public void setUp() {
        changeRequestAttribute = new ChangeRequestAttribute();
    }

    @Test
    public void testGetDataLength() {
        assertEquals(4, changeRequestAttribute.getDataLength(), "getDataLength() did not return the expected value");
    }
}
