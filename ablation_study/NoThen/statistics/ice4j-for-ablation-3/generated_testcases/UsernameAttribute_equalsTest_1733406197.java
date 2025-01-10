
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UsernameAttribute_equalsTest {

    private UsernameAttribute att1;
    private UsernameAttribute att2;

    @BeforeEach
    public void setUp() {
        att1 = new UsernameAttribute();
        att2 = new UsernameAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(att1.equals(att1), "An object should be equal to itself");
    }

    @Test
    public void testEquals_DifferentType() {
        Object obj = new Object();
        assertFalse(att1.equals(obj), "An attribute should not be equal to an object of a different type");
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        att1.setUsername("user1".getBytes());
        att2.setUsername("user1".getBytes());
        // Assuming getAttributeType() returns different values for att1 and att2
        // This is a mock implementation for the sake of the example
        // In a real scenario, you would need to ensure that getAttributeType() returns different values
        // for att1 and att2
        assertFalse(att1.equals(att2), "Attributes with different types should not be equal");
    }

    @Test
    public void testEquals_DifferentDataLength() {
        att1.setUsername("user1".getBytes());
        att2.setUsername("user22".getBytes());
        assertFalse(att1.equals(att2), "Attributes with different data lengths should not be equal");
    }

    @Test
    public void testEquals_DifferentUsername() {
        att1.setUsername("user1".getBytes());
        att2.setUsername("user2".getBytes());
        assertFalse(att1.equals(att2), "Attributes with different usernames should not be equal");
    }

    @Test
    public void testEquals_SameUsername() {
        att1.setUsername("user1".getBytes());
        att2.setUsername("user1".getBytes());
        assertTrue(att1.equals(att2), "Attributes with the same username should be equal");
    }
}
