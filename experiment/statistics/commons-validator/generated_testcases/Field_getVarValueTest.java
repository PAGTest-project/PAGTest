
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Field_getVarValueTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testGetVarValue_ExistingKey() {
        // Given
        Var var = new Var("testKey", "testValue", null);
        field.getVarMap().put("testKey", var);

        // When
        String result = field.getVarValue("testKey");

        // Then
        assertEquals("testValue", result);
    }

    @Test
    public void testGetVarValue_NonExistingKey() {
        // Given
        // No vars added to the map

        // When
        String result = field.getVarValue("nonExistingKey");

        // Then
        assertNull(result);
    }
}
