
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Field_addArgTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    private Arg createArg(String key) {
        Arg arg = new Arg();
        arg.setKey(key);
        return arg;
    }

    private Arg createArg(String key, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setPosition(position);
        return arg;
    }

    private Arg createArg(String key, String name) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        return arg;
    }

    private Arg createArg(String key, String name, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        arg.setPosition(position);
        return arg;
    }

    @Test
    public void testAddArgWithValidArg() {
        Arg arg = createArg("valid-key", "valid-name", 0);
        field.addArg(arg);

        assertEquals(arg, field.getArg(0), "testAddArgWithValidArg(1) ");
        assertEquals(arg, field.getArg("valid-name", 0), "testAddArgWithValidArg(2) ");
    }

    @Test
    public void testAddArgWithNullArg() {
        field.addArg(null);

        assertNull(field.getArg(0), "testAddArgWithNullArg(1) ");
    }

    @Test
    public void testAddArgWithNullKey() {
        Arg arg = createArg(null, "null-key-name", 0);
        field.addArg(arg);

        assertNull(field.getArg(0), "testAddArgWithNullKey(1) ");
    }

    @Test
    public void testAddArgWithEmptyKey() {
        Arg arg = createArg("", "empty-key-name", 0);
        field.addArg(arg);

        assertNull(field.getArg(0), "testAddArgWithEmptyKey(1) ");
    }

    @Test
    public void testAddArgWithNullName() {
        Arg arg = createArg("null-name-key", null, 0);
        field.addArg(arg);

        assertEquals(arg, field.getArg(0), "testAddArgWithNullName(1) ");
        assertEquals(arg, field.getArg(Field.DEFAULT_ARG, 0), "testAddArgWithNullName(2) ");
    }

    @Test
    public void testAddArgWithMultipleArgs() {
        field.addArg(createArg("key1", "name1", 0));
        field.addArg(createArg("key2", "name2", 1));
        field.addArg(createArg("key3", "name3", 2));

        assertEquals(3, field.getArgs("name1").length, "testAddArgWithMultipleArgs(1) ");
        assertEquals("key1", field.getArg("name1", 0).getKey(), "testAddArgWithMultipleArgs(2) ");
        assertEquals("key2", field.getArg("name2", 1).getKey(), "testAddArgWithMultipleArgs(3) ");
        assertEquals("key3", field.getArg("name3", 2).getKey(), "testAddArgWithMultipleArgs(4) ");
    }
}
