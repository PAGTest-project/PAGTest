
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
    public void testToStringWithNormalKeyAndValue() {
        assertEquals("{key=value}", singletonMap.toString());
    }

    @Test
    public void testToStringWithKeyAsMap() {
        singletonMap = new SingletonMap<>(new SingletonMap<>("key", "value"), "value");
        assertEquals("{(this Map)=value}", singletonMap.toString());
    }

    @Test
    public void testToStringWithValueAsMap() {
        singletonMap = new SingletonMap<>("key", new SingletonMap<>("key", "value"));
        assertEquals("{key=(this Map)}", singletonMap.toString());
    }

    @Test
    public void testToStringWithBothKeyAndValueAsMap() {
        singletonMap = new SingletonMap<>(new SingletonMap<>("key", "value"), new SingletonMap<>("key", "value"));
        assertEquals("{(this Map)=(this Map)}", singletonMap.toString());
    }

    @Test
    public void testToStringAfterSetValue() {
        singletonMap.setValue("newValue");
        assertEquals("{key=newValue}", singletonMap.toString());
    }
}
