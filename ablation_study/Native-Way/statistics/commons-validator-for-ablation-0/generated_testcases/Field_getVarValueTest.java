
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
        // Initialize hVars with some test data
        field.hVars.put("var-1-1", new Var("var-1-1", "value-1-1", "jstype-1-1"));
        field.hVars.put("var-2-1", new Var("var-2-1", "value-2-1", "jstype-2-1"));
    }

    @Test
    public void testGetVarValueExistingKey() {
        String value = field.getVarValue("var-1-1");
        assertEquals("value-1-1", value, "Value for existing key is incorrect");
    }

    @Test
    public void testGetVarValueNonExistingKey() {
        String value = field.getVarValue("non-existing-key");
        assertNull(value, "Value for non-existing key should be null");
    }
}
