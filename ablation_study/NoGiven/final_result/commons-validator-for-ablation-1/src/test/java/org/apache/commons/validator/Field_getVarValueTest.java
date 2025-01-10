
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
    public void testGetVarValueExists() {
        field.addVar("var-1-1", "value-1-1", "jstype-1-1");
        assertEquals("value-1-1", field.getVarValue("var-1-1"));
    }

    @Test
    public void testGetVarValueNotExists() {
        assertNull(field.getVarValue("non-existent-var"));
    }
}
