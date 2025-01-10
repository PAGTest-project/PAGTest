
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Field_getArgTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testGetArgWithValidPosition() {
        field.addArg(createArg("arg1", "name1", 0));
        field.addArg(createArg("arg2", "name2", 1));

        Arg arg = field.getArg("name1", 0);
        assertEquals("arg1", arg.getKey(), "Key mismatch for position 0");

        arg = field.getArg("name2", 1);
        assertEquals("arg2", arg.getKey(), "Key mismatch for position 1");
    }

    @Test
    public void testGetArgWithInvalidPosition() {
        field.addArg(createArg("arg1", "name1", 0));

        Arg arg = field.getArg("name1", 1);
        assertNull(arg, "Expected null for invalid position");
    }

    @Test
    public void testGetArgWithNullArgs() {
        Arg arg = field.getArg("name1", 0);
        assertNull(arg, "Expected null when args are null");
    }

    @Test
    public void testGetArgWithDefaultArg() {
        field.addArg(createArg("arg1", Field.DEFAULT_ARG, 0));

        Arg arg = field.getArg(Field.DEFAULT_ARG, 0);
        assertNull(arg, "Expected null for default arg");
    }

    @Test
    public void testGetArgWithRecursiveCall() {
        field.addArg(createArg("arg1", "name1", 0));

        Arg arg = field.getArg("name2", 0);
        assertNull(arg, "Expected null for recursive call");
    }

    private Arg createArg(String key, String name, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        arg.setPosition(position);
        return arg;
    }
}
