
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_userDataTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testUserDataWithExistingKey() {
        attributes.put("userDataKey", "userDataValue");
        assertEquals("userDataValue", attributes.userData("userDataKey"));
    }

    @Test
    public void testUserDataWithNonExistingKey() {
        assertNull(attributes.userData("nonExistingKey"));
    }

    @Test
    public void testUserDataWithNullKey() {
        assertThrows(IllegalArgumentException.class, () -> attributes.userData(null));
    }
}
