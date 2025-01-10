
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.apache.commons.collections4.MapIterator;

public class SingletonMap_setValueTest {

    @Test
    public void testSetValue() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "oldValue");
        MapIterator<String, String> iterator = map.mapIterator();
        iterator.next(); // Move iterator to the position where setValue can be called

        String oldValue = iterator.setValue("newValue");

        assertEquals("oldValue", oldValue);
        assertEquals("newValue", map.getValue());
    }
}
