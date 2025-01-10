
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Field_getKeyTest {

    @Test
    void testGetKey_keyIsNull() {
        Field field = new Field();
        field.property = "testProperty";
        field.indexedListProperty = "testIndexedListProperty";

        String key = field.getKey();

        assertEquals("testIndexedListProperty[]." + field.property, key);
    }

    @Test
    void testGetKey_keyIsNotNull() {
        Field field = new Field();
        field.key = "existingKey";

        String key = field.getKey();

        assertEquals("existingKey", key);
    }
}
