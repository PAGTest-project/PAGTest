
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsernameAttribute_equalsTest {

    private UsernameAttribute attribute1;
    private UsernameAttribute attribute2;

    @BeforeEach
    public void setUp() {
        attribute1 = new UsernameAttribute();
        attribute2 = new UsernameAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(attribute1.equals(attribute1), "An object should be equal to itself");
    }

    @Test
    public void testEquals_DifferentClass() {
        Object obj = new Object();
        assertFalse(attribute1.equals(obj), "An object of different class should not be equal");
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        attribute1.setUsername("user1".getBytes());
        attribute2.setUsername("user1".getBytes());
        attribute2.setAttributeType(Attribute.Type.UNKNOWN_ATTRIBUTE); // Set different attribute type
        assertFalse(attribute1.equals(attribute2), "Attributes with different types should not be equal");
    }

    @Test
    public void testEquals_DifferentDataLength() {
        attribute1.setUsername("user1".getBytes());
        attribute2.setUsername("user12".getBytes());
        assertFalse(attribute1.equals(attribute2), "Attributes with different data lengths should not be equal");
    }

    @Test
    public void testEquals_DifferentUsername() {
        attribute1.setUsername("user1".getBytes());
        attribute2.setUsername("user2".getBytes());
        assertFalse(attribute1.equals(attribute2), "Attributes with different usernames should not be equal");
    }

    @Test
    public void testEquals_EqualAttributes() {
        attribute1.setUsername("user1".getBytes());
        attribute2.setUsername("user1".getBytes());
        assertTrue(attribute1.equals(attribute2), "Attributes with the same username should be equal");
    }
}
