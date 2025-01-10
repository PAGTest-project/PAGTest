
package org.ice4j;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StackProperties_getStringTest {

    @Test
    public void testGetString_PropertyExistsAndNotEmpty() {
        System.setProperty("testProperty", "value");
        assertEquals("value", StackProperties.getString("testProperty"));
    }

    @Test
    public void testGetString_PropertyExistsButEmpty() {
        System.setProperty("testProperty", "   ");
        assertNull(StackProperties.getString("testProperty"));
    }

    @Test
    public void testGetString_PropertyDoesNotExist() {
        assertNull(StackProperties.getString("nonExistentProperty"));
    }
}
