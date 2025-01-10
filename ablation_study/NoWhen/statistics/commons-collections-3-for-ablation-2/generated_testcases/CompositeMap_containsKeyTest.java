
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_containsKeyTest {

    private Map<String, String> one;
    private Map<String, String> two;

    @BeforeEach
    public void setUp() {
        one = new HashMap<>();
        one.put("1", "one");
        one.put("2", "two");

        two = new HashMap<>();
        two.put("3", "three");
        two.put("4", "four");
    }

    @Test
    public void testContainsKey_KeyPresentInFirstMap() {
        CompositeMap<String, String> map = new CompositeMap<>(one, two);
        assertTrue(map.containsKey("1"));
    }

    @Test
    public void testContainsKey_KeyPresentInSecondMap() {
        CompositeMap<String, String> map = new CompositeMap<>(one, two);
        assertTrue(map.containsKey("3"));
    }

    @Test
    public void testContainsKey_KeyNotPresent() {
        CompositeMap<String, String> map = new CompositeMap<>(one, two);
        assertFalse(map.containsKey("5"));
    }

    @Test
    public void testContainsKey_AfterAddingNewMap() {
        CompositeMap<String, String> map = new CompositeMap<>(one, two);
        Map<String, String> three = new HashMap<>();
        three.put("5", "five");
        map.addComposited(three);
        assertTrue(map.containsKey("5"));
    }

    @Test
    public void testContainsKey_AfterRemovingKey() {
        CompositeMap<String, String> map = new CompositeMap<>(one, two);
        map.remove("1");
        assertFalse(map.containsKey("1"));
    }

    @Test
    public void testContainsKey_AfterRemovingCompositedMap() {
        CompositeMap<String, String> map = new CompositeMap<>(one, two);
        map.removeComposited(one);
        assertFalse(map.containsKey("1"));
    }
}
