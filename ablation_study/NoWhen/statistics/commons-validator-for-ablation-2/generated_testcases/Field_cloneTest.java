
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Field_cloneTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
    }

    @Test
    public void testCloneWithArgs() {
        Field clonedField = (Field) field.clone();

        assertNotNull(clonedField, "Cloned field is null.");
        assertEquals(field.getArgs("required").length, clonedField.getArgs("required").length, "Args length mismatch.");

        for (int i = 0; i < field.getArgs("required").length; i++) {
            assertEquals(field.getArg("required", i).getKey(), clonedField.getArg("required", i).getKey(), "Arg key mismatch at position " + i);
        }
    }

    @Test
    public void testCloneWithNullArgs() {
        field.args = new Map[5];
        field.args[0] = new HashMap<>();
        field.args[2] = new HashMap<>();

        Field clonedField = (Field) field.clone();

        assertNotNull(clonedField, "Cloned field is null.");
        assertEquals(field.args.length, clonedField.args.length, "Args array length mismatch.");

        for (int i = 0; i < field.args.length; i++) {
            if (field.args[i] == null) {
                assertNull(clonedField.args[i], "Cloned args at position " + i + " should be null.");
            } else {
                assertNotNull(clonedField.args[i], "Cloned args at position " + i + " should not be null.");
            }
        }
    }

    private Arg createArg(String key) {
        return createArg(key, null, -1);
    }

    private Arg createArg(String key, String name) {
        return createArg(key, name, -1);
    }

    private Arg createArg(String key, int position) {
        return createArg(key, null, position);
    }

    private Arg createArg(String key, String name, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        arg.setPosition(position);
        return arg;
    }
}
