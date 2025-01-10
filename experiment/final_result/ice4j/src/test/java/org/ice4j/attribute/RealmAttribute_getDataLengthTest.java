
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class RealmAttribute_getDataLengthTest {
    private RealmAttribute realmAttribute;

    @BeforeEach
    public void setUp() {
        realmAttribute = new RealmAttribute();
    }

    @Test
    public void testGetDataLength() {
        byte[] realmValue = "exampleRealm".getBytes();
        realmAttribute.setRealm(realmValue);
        assertEquals(realmValue.length, realmAttribute.getDataLength());
    }
}
