
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonMap_setValueTest {
    private SingletonMap<String, String> singletonMap;

    @BeforeEach
    public void setUp() {
        singletonMap = new SingletonMap<>("key", "value");
    }

    @Test
    public void testSetValue() {
        String oldValue = singletonMap.setValue("newValue");
        assertEquals("value", oldValue);
        assertEquals("newValue", singletonMap.getValue());
    }

    @Test
    public void testSetValueWithNull() {
        String oldValue = singletonMap.setValue(null);
        assertEquals("value", oldValue);
        assertEquals(null, singletonMap.getValue());
    }

    @Test
    public void testSetValueAndCheckWithGet() {
        singletonMap.setValue("newValue");
        assertEquals("newValue", singletonMap.get("key"));
    }

    @Test
    public void testSetValueAndCheckWithGetValue() {
        singletonMap.setValue("newValue");
        assertEquals("newValue", singletonMap.getValue());
    }
}
