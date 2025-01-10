
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
        field.addArg(createArg("key1", "name1", 0));
        field.addArg(createArg("key2", "name2", 1));

        Arg arg = field.getArg("key1", 0);
        assertEquals("key1", arg.getKey());
        assertEquals("name1", arg.getName());

        arg = field.getArg("key2", 1);
        assertEquals("key2", arg.getKey());
        assertEquals("name2", arg.getName());
    }

    @Test
    public void testGetArgWithInvalidPosition() {
        field.addArg(createArg("key1", "name1", 0));

        Arg arg = field.getArg("key1", 1);
        assertNull(arg);
    }

    @Test
    public void testGetArgWithDefaultArg() {
        field.addArg(createArg("key1", "name1", 0));
        field.addArg(createArg(Field.DEFAULT_ARG, "defaultName", 0));

        Arg arg = field.getArg("key1", 0);
        assertEquals("key1", arg.getKey());
        assertEquals("name1", arg.getName());

        arg = field.getArg(Field.DEFAULT_ARG, 0);
        assertNull(arg);
    }

    @Test
    public void testGetArgWithNullArg() {
        field.addArg(createArg("key1", "name1", 0));

        Arg arg = field.getArg("nonExistentKey", 0);
        assertNull(arg);
    }

    private Arg createArg(String key, String name, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        arg.setPosition(position);
        return arg;
    }
}
