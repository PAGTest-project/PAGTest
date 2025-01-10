
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Field_getKeyTest {

    @Test
    public void testGetKey_KeyIsNull() {
        Field field = new Field();
        field.property = "testProperty";
        field.indexedListProperty = "testIndexedListProperty";
        field.indexedProperty = "testIndexedProperty";

        String key = field.getKey();

        assertEquals("testIndexedListProperty[].testProperty", key);
    }

    @Test
    public void testGetKey_KeyIsNotNull() {
        Field field = new Field();
        field.key = "existingKey";

        String key = field.getKey();

        assertEquals("existingKey", key);
    }
}
