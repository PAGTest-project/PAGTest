
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
    public void testAddArgWithNullArg() {
        field.addArg(null);
        assertEquals(0, field.getArgs(Field.DEFAULT_ARG).length);
    }

    @Test
    public void testAddArgWithNullKey() {
        Arg arg = createArg(null);
        field.addArg(arg);
        assertEquals(0, field.getArgs(Field.DEFAULT_ARG).length);
    }

    @Test
    public void testAddArgWithEmptyKey() {
        Arg arg = createArg("");
        field.addArg(arg);
        assertEquals(0, field.getArgs(Field.DEFAULT_ARG).length);
    }

    @Test
    public void testAddArgWithValidArg() {
        Arg arg = createArg("valid-key", "valid-name");
        field.addArg(arg);
        assertEquals(1, field.getArgs("valid-name").length);
        assertEquals("valid-key", field.getArg("valid-name", 0).getKey());
    }

    @Test
    public void testAddArgWithPosition() {
        Arg arg1 = createArg("key1", "name1", 0);
        Arg arg2 = createArg("key2", "name2", 1);
        field.addArg(arg1);
        field.addArg(arg2);
        assertEquals(2, field.getArgs("name2").length);
        assertEquals("key1", field.getArg("name1", 0).getKey());
        assertEquals("key2", field.getArg("name2", 1).getKey());
    }

    @Test
    public void testAddArgWithDefaultPosition() {
        Arg arg1 = createArg("key1", "name1");
        Arg arg2 = createArg("key2", "name2");
        field.addArg(arg1);
        field.addArg(arg2);
        assertEquals(2, field.getArgs("name2").length);
        assertEquals("key1", field.getArg("name1", 0).getKey());
        assertEquals("key2", field.getArg("name2", 1).getKey());
    }

    @Test
    public void testAddArgWithMixedPositions() {
        Arg arg1 = createArg("key1", "name1", 0);
        Arg arg2 = createArg("key2", "name2");
        Arg arg3 = createArg("key3", "name3", 2);
        field.addArg(arg1);
        field.addArg(arg2);
        field.addArg(arg3);
        assertEquals(3, field.getArgs("name3").length);
        assertEquals("key1", field.getArg("name1", 0).getKey());
        assertEquals("key2", field.getArg("name2", 1).getKey());
        assertEquals("key3", field.getArg("name3", 2).getKey());
    }
}
