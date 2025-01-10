
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Field_generateKeyTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testGenerateKeyIndexed() {
        field.indexedListProperty = "indexedList";
        field.property = "property";
        field.generateKey();
        assertEquals("indexedList[]." + field.property, field.key);
    }

    @Test
    public void testGenerateKeyNotIndexed() {
        field.indexedListProperty = null;
        field.property = "property";
        field.generateKey();
        assertEquals(field.property, field.key);
    }
}
