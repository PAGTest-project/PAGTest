
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.junit.jupiter.api.*;

public class RealmAttribute_equalsTest {
    private RealmAttribute realmAttribute;
    private String realmValue = "exampleRealm";

    @BeforeEach
    public void setUp() throws Exception {
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
    public void testEquals_DifferentAttributeType() {
        RealmAttribute differentTypeAttribute = new RealmAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        differentTypeAttribute.setRealm(realmValue.getBytes());
        assertFalse(realmAttribute.equals(differentTypeAttribute));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        RealmAttribute differentLengthAttribute = new RealmAttribute();
        differentLengthAttribute.setRealm("differentLength".getBytes());
        assertFalse(realmAttribute.equals(differentLengthAttribute));
    }

    @Test
    public void testEquals_DifferentRealm() {
        RealmAttribute differentRealmAttribute = new RealmAttribute();
        differentRealmAttribute.setRealm("differentRealm".getBytes());
        assertFalse(realmAttribute.equals(differentRealmAttribute));
    }

    @Test
    public void testEquals_EqualAttributes() {
        RealmAttribute equalAttribute = new RealmAttribute();
        equalAttribute.setRealm(realmValue.getBytes());
        assertTrue(realmAttribute.equals(equalAttribute));
    }
}
