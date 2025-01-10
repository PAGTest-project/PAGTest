
package org.ice4j.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionIdAttribute_equalsTest {
    private ConnectionIdAttribute connectionIdAttribute;

    @BeforeEach
    public void setUp() {
        connectionIdAttribute = new ConnectionIdAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(connectionIdAttribute.equals(connectionIdAttribute));
    }

    @Test
    public void testEquals_DifferentType() {
        Object obj = new Object();
        assertFalse(connectionIdAttribute.equals(obj));
    }

    @Test
    public void testEquals_DifferentConnectionIdValue() {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        other.setConnectionIdValue(12345);
        connectionIdAttribute.setConnectionIdValue(54321);
        assertFalse(connectionIdAttribute.equals(other));
    }

    @Test
    public void testEquals_SameConnectionIdValue() {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        other.setConnectionIdValue(12345);
        connectionIdAttribute.setConnectionIdValue(12345);
        assertTrue(connectionIdAttribute.equals(other));
    }
}
