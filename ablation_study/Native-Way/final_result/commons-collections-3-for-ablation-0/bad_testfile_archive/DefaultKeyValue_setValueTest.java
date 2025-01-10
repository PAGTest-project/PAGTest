
package org.apache.commons.collections4.keyvalue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultKeyValue_setValueTest {

    private DefaultKeyValue<String, String> defaultKeyValue;

    @BeforeEach
    public void setUp() {
        defaultKeyValue = new DefaultKeyValue<>("key", "value");
    }

    @Test
    public void testSetValueSuccess() {
        String newValue = "newValue";
        String oldValue = defaultKeyValue.setValue(newValue);
        assertEquals(newValue, defaultKeyValue.getValue());
        assertEquals("value", oldValue);
    }

    @Test
    public void testSetValueSelfReference() {
        assertThrows(IllegalArgumentException.class, () -> {
            defaultKeyValue.setValue(defaultKeyValue);
        });
    }
}
