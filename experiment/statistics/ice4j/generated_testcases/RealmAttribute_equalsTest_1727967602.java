
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RealmAttribute_equalsTest {

    private RealmAttribute realmAttribute1;
    private RealmAttribute realmAttribute2;

    @BeforeEach
    public void setUp() {
        realmAttribute1 = new RealmAttribute();
        realmAttribute2 = new RealmAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(realmAttribute1.equals(realmAttribute1), "An object should be equal to itself");
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(realmAttribute1.equals("Not a RealmAttribute"), "A RealmAttribute should not be equal to a non-RealmAttribute object");
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        realmAttribute1.setRealm("realm1".getBytes());
        realmAttribute2.setRealm("realm1".getBytes());
        // Simulate different attribute type by modifying the type directly (assuming getAttributeType() is accessible)
        // This is a workaround since we don't have direct access to modify attribute type
        // In a real scenario, you would need to modify the attribute type in a way that is accessible
        // For example, by adding a setter method for attribute type in RealmAttribute class
        realmAttribute2.setAttributeType(realmAttribute1.getAttributeType() + 1); // Ensure different types
        assertFalse(realmAttribute1.equals(realmAttribute2), "Attributes with different types should not be equal");
    }

    @Test
    public void testEquals_DifferentDataLength() {
        realmAttribute1.setRealm("realm1".getBytes());
        realmAttribute2.setRealm("realm".getBytes()); // Different length
        assertFalse(realmAttribute1.equals(realmAttribute2), "Attributes with different data lengths should not be equal");
    }

    @Test
    public void testEquals_DifferentRealm() {
        realmAttribute1.setRealm("realm1".getBytes());
        realmAttribute2.setRealm("realm2".getBytes()); // Different content
        assertFalse(realmAttribute1.equals(realmAttribute2), "Attributes with different realm values should not be equal");
    }

    @Test
    public void testEquals_EqualAttributes() {
        realmAttribute1.setRealm("realm1".getBytes());
        realmAttribute2.setRealm("realm1".getBytes());
        assertTrue(realmAttribute1.equals(realmAttribute2), "Attributes with the same realm values should be equal");
    }
}
