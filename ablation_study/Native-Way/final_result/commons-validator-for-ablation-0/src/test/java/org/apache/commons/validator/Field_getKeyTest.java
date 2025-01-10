
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        // Ensure key is null initially
        assertNull(field.getKey(), "Initial key should be null");

        // Set a property to ensure generateKey() works
        field.property = "testProperty";

        // Generate key and check
        String generatedKey = field.getKey();
        assertNotNull(generatedKey, "Generated key should not be null");
        assertEquals(field.getProperty(), generatedKey, "Generated key should match property");
    }

    @Test
    public void testGetKeyWithNonNullKey() {
        // Set a key manually
        field.key = "testKey";

        // Retrieve the key and check
        String retrievedKey = field.getKey();
        assertNotNull(retrievedKey, "Retrieved key should not be null");
        assertEquals("testKey", retrievedKey, "Retrieved key should match the set key");
    }

    @Test
    public void testGenerateKeyIndexed() {
        // Set indexed properties
        field.indexedListProperty = "indexedList";
        field.property = "property";

        // Generate key and check
        field.generateKey();
        String generatedKey = field.getKey();
        assertNotNull(generatedKey, "Generated key should not be null");
        assertEquals("indexedList[]." + field.property, generatedKey, "Generated key should match indexed property format");
    }

    @Test
    public void testGenerateKeyNonIndexed() {
        // Set non-indexed property
        field.property = "property";

        // Generate key and check
        field.generateKey();
        String generatedKey = field.getKey();
        assertNotNull(generatedKey, "Generated key should not be null");
        assertEquals(field.property, generatedKey, "Generated key should match property");
    }
}
