
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
    public void testGetArgWithValidKeyAndPosition() {
        Arg arg1 = createArg("key1", "name1", 0);
        Arg arg2 = createArg("key2", "name2", 1);
        field.addArg(arg1);
        field.addArg(arg2);

        assertEquals(arg1, field.getArg("name1", 0));
        assertEquals(arg2, field.getArg("name2", 1));
    }

    @Test
    public void testGetArgWithInvalidPosition() {
        Arg arg1 = createArg("key1", "name1", 0);
        field.addArg(arg1);

        assertNull(field.getArg("name1", 1));
    }

    @Test
    public void testGetArgWithNullArgAtPosition() {
        Arg arg1 = createArg("key1", "name1", 0);
        field.addArg(arg1);

        assertNull(field.getArg("name1", 1));
    }

    @Test
    public void testGetArgWithDefaultArg() {
        Arg arg1 = createArg("key1", "name1", 0);
        Arg defaultArg = createArg(Field.DEFAULT_ARG, "defaultName", 0);
        field.addArg(arg1);
        field.addArg(defaultArg);

        assertEquals(arg1, field.getArg("name1", 0));
        assertNull(field.getArg(Field.DEFAULT_ARG, 0));
    }

    @Test
    public void testGetArgWithRecursiveCall() {
        Arg arg1 = createArg("key1", "name1", 0);
        Arg defaultArg = createArg(Field.DEFAULT_ARG, "defaultName", 0);
        field.addArg(arg1);
        field.addArg(defaultArg);

        assertEquals(arg1, field.getArg("name1", 0));
        assertEquals(defaultArg, field.getArg(0));
    }

    private Arg createArg(String key, String name, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        arg.setPosition(position);
        return arg;
    }
}
