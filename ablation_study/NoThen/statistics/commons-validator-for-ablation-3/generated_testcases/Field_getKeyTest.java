
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Field_getKeyTest {

    @Test
    void testGetKey_KeyIsNull() {
        Field field = new Field();
        field.property = "testProperty";
        field.indexedListProperty = null;

        String result = field.getKey();

        assertEquals("testProperty", result);
    }

    @Test
    void testGetKey_KeyIsNotNull() {
        Field field = new Field();
        field.key = "existingKey";

        String result = field.getKey();

        assertEquals("existingKey", result);
    }

    @Test
    void testGetKey_IndexedProperty() {
        Field field = new Field();
        field.property = "testProperty";
        field.indexedListProperty = "testIndexedListProperty";

        String result = field.getKey();

        assertEquals("testIndexedListProperty[]." + "testProperty", result);
    }
}
