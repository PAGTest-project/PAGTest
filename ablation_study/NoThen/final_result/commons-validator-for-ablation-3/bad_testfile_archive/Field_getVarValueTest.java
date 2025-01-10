
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
        Var var = new Var("testKey", "testValue", null);
        field.addVar(var);
        assertEquals("testValue", field.getVarValue("testKey"));
    }

    @Test
    public void testGetVarValueNotExists() {
        assertNull(field.getVarValue("nonExistentKey"));
    }
}
