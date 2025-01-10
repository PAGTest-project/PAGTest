
package org.apache.commons.collections4.keyvalue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultKeyValue_hashCodeTest {

    private DefaultKeyValue<String, String> defaultKeyValue;

    @BeforeEach
    public void setUp() {
        defaultKeyValue = new DefaultKeyValue<>();
    }

    @Test
    public void testHashCodeWithNonNullKeyAndValue() {
        defaultKeyValue.setKey("key");
        defaultKeyValue.setValue("value");
        int expectedHashCode = "key".hashCode() ^ "value".hashCode();
        assertEquals(expectedHashCode, defaultKeyValue.hashCode());
    }

    @Test
    public void testHashCodeWithNullKeyAndNonNullValue() {
        defaultKeyValue.setKey(null);
        defaultKeyValue.setValue("value");
        int expectedHashCode = 0 ^ "value".hashCode();
        assertEquals(expectedHashCode, defaultKeyValue.hashCode());
    }

    @Test
    public void testHashCodeWithNonNullKeyAndNullValue() {
        defaultKeyValue.setKey("key");
        defaultKeyValue.setValue(null);
        int expectedHashCode = "key".hashCode() ^ 0;
        assertEquals(expectedHashCode, defaultKeyValue.hashCode());
    }

    @Test
    public void testHashCodeWithNullKeyAndNullValue() {
        defaultKeyValue.setKey(null);
        defaultKeyValue.setValue(null);
        int expectedHashCode = 0 ^ 0;
        assertEquals(expectedHashCode, defaultKeyValue.hashCode());
    }
}
