
package org.ice4j;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackProperties_getBooleanTest {

    @Test
    public void testGetBoolean_PropertyExistsAndIsTrue() {
        System.setProperty("test.boolean.property", "true");
        boolean result = StackProperties.getBoolean("test.boolean.property", false);
        assertTrue(result);
    }

    @Test
    public void testGetBoolean_PropertyExistsAndIsFalse() {
        System.setProperty("test.boolean.property", "false");
        boolean result = StackProperties.getBoolean("test.boolean.property", true);
        assertFalse(result);
    }

    @Test
    public void testGetBoolean_PropertyDoesNotExist() {
        System.clearProperty("test.boolean.property");
        boolean result = StackProperties.getBoolean("test.boolean.property", true);
        assertTrue(result);
    }
}
