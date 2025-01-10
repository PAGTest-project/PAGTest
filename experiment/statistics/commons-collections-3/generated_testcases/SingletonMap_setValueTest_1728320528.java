
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SingletonMap_setValueTest {

    @Test
    public void testSetValue() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "oldValue");
        SingletonMapIterator<String, String> iterator = new SingletonMapIterator<>(map);
        iterator.next(); // Move iterator to the position where setValue can be called

        String oldValue = iterator.setValue("newValue");

        assertEquals("oldValue", oldValue);
        assertEquals("newValue", map.getValue());
    }
}
