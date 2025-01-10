
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_hashCodeTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testHashCodeWithEmptyMap() {
        CompositeMap<String, String> emptyMap = new CompositeMap<>();
        assertEquals(0, emptyMap.hashCode());
    }

    @Test
    public void testHashCodeWithNonEmptyMap() {
        int expectedHashCode = map1.hashCode() + map2.hashCode();
        assertEquals(expectedHashCode, compositeMap.hashCode());
    }

    @Test
    public void testHashCodeAfterAddingMap() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("key5", "value5");
        compositeMap.addComposited(map3);

        int expectedHashCode = map1.hashCode() + map2.hashCode() + map3.hashCode();
        assertEquals(expectedHashCode, compositeMap.hashCode());
    }

    @Test
    public void testHashCodeAfterRemovingMap() {
        compositeMap.removeComposited(map2);
        int expectedHashCode = map1.hashCode();
        assertEquals(expectedHashCode, compositeMap.hashCode());
    }

    @Test
    public void testHashCodeAfterModifyingMap() {
        map1.put("key6", "value6");
        int expectedHashCode = map1.hashCode() + map2.hashCode();
        assertEquals(expectedHashCode, compositeMap.hashCode());
    }

    @Test
    public void testHashCodeConsistency() {
        int initialHashCode = compositeMap.hashCode();
        map1.put("key7", "value7");
        int modifiedHashCode = compositeMap.hashCode();
        assertNotEquals(initialHashCode, modifiedHashCode);
    }
}
