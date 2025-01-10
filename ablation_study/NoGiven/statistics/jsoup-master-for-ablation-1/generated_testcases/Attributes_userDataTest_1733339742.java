
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
        attributes.addObject(SharedConstants.UserDataKey, "userDataValue");
        Object result = attributes.userData("userDataKey");
        assertEquals("userDataValue", result);
    }

    @Test
    public void testUserDataWithNonExistingKey() {
        Object result = attributes.userData("nonExistingKey");
        assertNull(result);
    }

    @Test
    public void testUserDataWithNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            attributes.userData(null);
        });
    }
}
