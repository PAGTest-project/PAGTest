
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class RealmAttribute_equalsTest {
    private RealmAttribute realmAttribute;
    private String realmValue;

    @BeforeEach
    public void setUp() throws Exception {
        realmValue = "exampleRealm";
        realmAttribute = new RealmAttribute();
        realmAttribute.setRealm(realmValue.getBytes());
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(realmAttribute.equals(realmAttribute));
    }

    @Test
    public void testEquals_DifferentType() {
        Object obj = new Object();
        assertFalse(realmAttribute.equals(obj));
    }

    @Test
    public void testEquals_DifferentRealm() {
        RealmAttribute differentRealmAttribute = new RealmAttribute();
        differentRealmAttribute.setRealm("differentRealm".getBytes());
        assertFalse(realmAttribute.equals(differentRealmAttribute));
    }

    @Test
    public void testEquals_SameRealm() {
        RealmAttribute sameRealmAttribute = new RealmAttribute();
        sameRealmAttribute.setRealm(realmValue.getBytes());
        assertTrue(realmAttribute.equals(sameRealmAttribute));
    }

    @Test
    public void testEquals_DifferentLength() {
        RealmAttribute differentLengthAttribute = new RealmAttribute();
        differentLengthAttribute.setRealm("short".getBytes());
        assertFalse(realmAttribute.equals(differentLengthAttribute));
    }

    @Test
    public void testEquals_NullRealm() {
        RealmAttribute nullRealmAttribute = new RealmAttribute();
        nullRealmAttribute.setRealm(null);
        assertFalse(realmAttribute.equals(nullRealmAttribute));
    }
}
