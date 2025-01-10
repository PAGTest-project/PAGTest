
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UsernameAttribute_getNameTest {
    private UsernameAttribute usernameAttribute;

    @BeforeEach
    public void setUp() {
        usernameAttribute = new UsernameAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("USERNAME", usernameAttribute.getName());
    }
}
