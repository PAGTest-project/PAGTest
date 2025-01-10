
package org.apache.commons.collections4.keyvalue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultKeyValue_equalsTest {

    private DefaultKeyValue<String, String> kv1;
    private DefaultKeyValue<String, String> kv2;

    @BeforeEach
    public void setUp() {
        kv1 = new DefaultKeyValue<>("key1", "value1");
        kv2 = new DefaultKeyValue<>("key2", "value2");
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(kv1.equals(kv1));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(kv1.equals("not a DefaultKeyValue"));
    }

    @Test
    public void testEqualsSameKeyAndValue() {
        DefaultKeyValue<String, String> kv3 = new DefaultKeyValue<>("key1", "value1");
        assertTrue(kv1.equals(kv3));
    }

    @Test
    public void testEqualsDifferentKey() {
        assertFalse(kv1.equals(kv2));
    }

    @Test
    public void testEqualsNullKeyAndValue() {
        DefaultKeyValue<String, String> kvNull1 = new DefaultKeyValue<>(null, null);
        DefaultKeyValue<String, String> kvNull2 = new DefaultKeyValue<>(null, null);
        assertTrue(kvNull1.equals(kvNull2));
    }

    @Test
    public void testEqualsNullKeyNonNullValue() {
        DefaultKeyValue<String, String> kvNullKey = new DefaultKeyValue<>(null, "value1");
        assertFalse(kv1.equals(kvNullKey));
    }

    @Test
    public void testEqualsNonNullKeyNullValue() {
        DefaultKeyValue<String, String> kvNullValue = new DefaultKeyValue<>("key1", null);
        assertFalse(kv1.equals(kvNullValue));
    }

    @Test
    public void testEqualsDifferentKeyNullValue() {
        DefaultKeyValue<String, String> kvNullValue1 = new DefaultKeyValue<>("key1", null);
        DefaultKeyValue<String, String> kvNullValue2 = new DefaultKeyValue<>("key2", null);
        assertFalse(kvNullValue1.equals(kvNullValue2));
    }

    @Test
    public void testEqualsNullKeyDifferentValue() {
        DefaultKeyValue<String, String> kvNullKey1 = new DefaultKeyValue<>(null, "value1");
        DefaultKeyValue<String, String> kvNullKey2 = new DefaultKeyValue<>(null, "value2");
        assertFalse(kvNullKey1.equals(kvNullKey2));
    }
}
