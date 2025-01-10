
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
        attributes.put(SharedConstants.UserDataKey, "testKey", "testValue");
        assertEquals("testValue", attributes.userData("testKey"));
    }

    @Test
    public void testUserDataWithNonExistingKey() {
        assertNull(attributes.userData("nonExistingKey"));
    }

    @Test
    public void testUserDataWithNullKey() {
        assertThrows(IllegalArgumentException.class, () -> attributes.userData(null));
    }

    @Test
    public void testUserDataWithNoUserData() {
        assertNull(attributes.userData("testKey"));
    }

    @Test
    public void testUserDataWithMultipleEntries() {
        attributes.put(SharedConstants.UserDataKey, "key1", "value1");
        attributes.put(SharedConstants.UserDataKey, "key2", "value2");
        assertEquals("value1", attributes.userData("key1"));
        assertEquals("value2", attributes.userData("key2"));
    }
}
