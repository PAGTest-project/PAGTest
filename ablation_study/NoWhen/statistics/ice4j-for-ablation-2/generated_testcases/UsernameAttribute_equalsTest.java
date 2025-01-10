
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UsernameAttribute_equalsTest {
    private UsernameAttribute usernameAttribute;
    private String usernameValue;

    @BeforeEach
    public void setUp() throws Exception {
        usernameValue = "testUsername";
        usernameAttribute = new UsernameAttribute();
        usernameAttribute.setUsername(usernameValue.getBytes());
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(usernameAttribute.equals(usernameAttribute));
    }

    @Test
    public void testEquals_DifferentObjectType() {
        assertFalse(usernameAttribute.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentUsername() {
        UsernameAttribute differentUsernameAttribute = new UsernameAttribute();
        differentUsernameAttribute.setUsername("differentUsername".getBytes());
        assertFalse(usernameAttribute.equals(differentUsernameAttribute));
    }

    @Test
    public void testEquals_SameUsername() {
        UsernameAttribute sameUsernameAttribute = new UsernameAttribute();
        sameUsernameAttribute.setUsername(usernameValue.getBytes());
        assertTrue(usernameAttribute.equals(sameUsernameAttribute));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        UsernameAttribute differentTypeAttribute = new UsernameAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        differentTypeAttribute.setUsername(usernameValue.getBytes());
        assertFalse(usernameAttribute.equals(differentTypeAttribute));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        UsernameAttribute differentLengthAttribute = new UsernameAttribute();
        differentLengthAttribute.setUsername("short".getBytes());
        assertFalse(usernameAttribute.equals(differentLengthAttribute));
    }
}
