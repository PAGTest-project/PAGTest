
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UsernameAttribute_getDataLengthTest {
    private UsernameAttribute usernameAttribute;

    @BeforeEach
    public void setUp() {
        usernameAttribute = new UsernameAttribute();
    }

    @Test
    public void testGetDataLength() {
        byte[] usernameValue = "testUsername".getBytes();
        usernameAttribute.setUsername(usernameValue);
        assertEquals(usernameValue.length, usernameAttribute.getDataLength());
    }
}
