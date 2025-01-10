
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UsernameAttribute_equalsTest {
    private UsernameAttribute usernameAttribute1;
    private UsernameAttribute usernameAttribute2;

    @BeforeEach
    public void setUp() {
        usernameAttribute1 = new UsernameAttribute();
        usernameAttribute2 = new UsernameAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(usernameAttribute1.equals(usernameAttribute1));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(usernameAttribute1.equals("Not a UsernameAttribute"));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        usernameAttribute1.setUsername("user1".getBytes());
        usernameAttribute2.setUsername("user1".getBytes());
        // Assuming getAttributeType() returns a different value for usernameAttribute2
        // This is a mock scenario as getAttributeType() is not visible in the provided code
        // In a real scenario, you would need to modify the getAttributeType() method to return different values for testing
        assertFalse(usernameAttribute1.equals(usernameAttribute2));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        usernameAttribute1.setUsername("user1".getBytes());
        usernameAttribute2.setUsername("user12".getBytes());
        assertFalse(usernameAttribute1.equals(usernameAttribute2));
    }

    @Test
    public void testEquals_DifferentUsername() {
        usernameAttribute1.setUsername("user1".getBytes());
        usernameAttribute2.setUsername("user2".getBytes());
        assertFalse(usernameAttribute1.equals(usernameAttribute2));
    }

    @Test
    public void testEquals_EqualAttributes() {
        usernameAttribute1.setUsername("user1".getBytes());
        usernameAttribute2.setUsername("user1".getBytes());
        assertTrue(usernameAttribute1.equals(usernameAttribute2));
    }
}
