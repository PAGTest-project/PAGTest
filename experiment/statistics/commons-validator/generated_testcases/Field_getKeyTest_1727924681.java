
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Field_getKeyTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testGetKeyWithNullKey() {
        // Given
        field.property = "testProperty";
        field.indexedListProperty = null;

        // When
        String result = field.getKey();

        // Then
        assertEquals("testProperty", result);
    }

    @Test
    public void testGetKeyWithIndexedProperty() {
        // Given
        field.property = "testProperty";
        field.indexedListProperty = "testIndexedListProperty";

        // When
        String result = field.getKey();

        // Then
        assertEquals("testIndexedListProperty[]", result);
    }
}
