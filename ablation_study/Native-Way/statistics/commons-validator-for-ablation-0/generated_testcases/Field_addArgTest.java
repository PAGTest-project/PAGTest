
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
        Arg arg = createArg("validKey", "validName", 0);
        field.addArg(arg);
        assertEquals(1, field.getArgs("validName").length, "testAddArgWithValidArg(1) ");
        assertEquals("validKey", field.getArg("validName", 0).getKey(), "testAddArgWithValidArg(2) ");
    }

    @Test
    public void testAddArgWithNullArg() {
        field.addArg(null);
        assertEquals(0, field.getArgs(Field.DEFAULT_ARG).length, "testAddArgWithNullArg(1) ");
    }

    @Test
    public void testAddArgWithNullKey() {
        Arg arg = createArg(null, "nullKeyName", 0);
        field.addArg(arg);
        assertEquals(0, field.getArgs(Field.DEFAULT_ARG).length, "testAddArgWithNullKey(1) ");
    }

    @Test
    public void testAddArgWithEmptyKey() {
        Arg arg = createArg("", "emptyKeyName", 0);
        field.addArg(arg);
        assertEquals(0, field.getArgs(Field.DEFAULT_ARG).length, "testAddArgWithEmptyKey(1) ");
    }

    @Test
    public void testAddArgWithDefaultArg() {
        Arg arg = createArg("defaultKey", null, 0);
        field.addArg(arg);
        assertEquals(1, field.getArgs(Field.DEFAULT_ARG).length, "testAddArgWithDefaultArg(1) ");
        assertEquals("defaultKey", field.getArg(Field.DEFAULT_ARG, 0).getKey(), "testAddArgWithDefaultArg(2) ");
    }

    @Test
    public void testAddArgWithPositionOverride() {
        field.addArg(createArg("position1", "position", 1));
        field.addArg(createArg("position0", "position", 0));
        field.addArg(createArg("default0"));
        field.addArg(createArg("default1"));

        assertEquals(2, field.getArgs("position").length, "testAddArgWithPositionOverride(1) ");
        assertEquals("position0", field.getArg("position", 0).getKey(), "testAddArgWithPositionOverride(2) ");
        assertEquals("position1", field.getArg("position", 1).getKey(), "testAddArgWithPositionOverride(3) ");

        assertEquals(2, field.getArgs(Field.DEFAULT_ARG).length, "testAddArgWithPositionOverride(4) ");
        assertEquals("default0", field.getArg(Field.DEFAULT_ARG, 0).getKey(), "testAddArgWithPositionOverride(5) ");
        assertEquals("default1", field.getArg(Field.DEFAULT_ARG, 1).getKey(), "testAddArgWithPositionOverride(6) ");
    }
}
