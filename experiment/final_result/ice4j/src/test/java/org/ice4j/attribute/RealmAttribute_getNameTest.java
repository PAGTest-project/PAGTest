
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class RealmAttribute_getNameTest {
    private RealmAttribute realmAttribute;

    @BeforeEach
    public void setUp() {
        realmAttribute = new RealmAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("REALM", realmAttribute.getName());
    }
}
