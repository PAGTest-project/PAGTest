
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonMap_toStringTest {
    private SingletonMap<String, String> singletonMap;

    @BeforeEach
    public void setUp() {
        singletonMap = new SingletonMap<>("key", "value");
    }

    @Test
    public void testToStringWithKeyAndValue() {
        String expected = "{key=value}";
        assertEquals(expected, singletonMap.toString());
    }

    @Test
    public void testToStringWithThisMapAsKey() {
        singletonMap = new SingletonMap<>(singletonMap, "value");
        String expected = "{(this Map)=value}";
        assertEquals(expected, singletonMap.toString());
    }

    @Test
    public void testToStringWithThisMapAsValue() {
        singletonMap = new SingletonMap<>("key", singletonMap);
        String expected = "{key=(this Map)}";
        assertEquals(expected, singletonMap.toString());
    }

    @Test
    public void testToStringWithNullKeyAndValue() {
        singletonMap = new SingletonMap<>(null, null);
        String expected = "{null=null}";
        assertEquals(expected, singletonMap.toString());
    }
}
