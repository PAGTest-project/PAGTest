
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class Field_addArgTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
        field.args = new Map[2]; // Initialize args array with sufficient capacity
    }

    private Arg createArg(String key) {
        Arg arg = new Arg();
        arg.setKey(key);
        return arg;
    }

    private Arg createArg(String key, int position) {
        Arg arg = createArg(key);
        arg.setPosition(position);
        return arg;
    }

    private Arg createArg(String key, String name) {
        Arg arg = createArg(key);
        arg.setName(name);
        return arg;
    }

    private Arg createArg(String key, String name, int position) {
        Arg arg = createArg(key, name);
        arg.setPosition(position);
        return arg;
    }

    @Test
    public void testAddArgWithValidArg() {
        Arg arg = createArg("validKey", "validName", 0);
        field.addArg(arg);
        assertEquals(arg, field.getArg(0), "Arg should be added correctly");
    }

    @Test
    public void testAddArgWithNullArg() {
        field.addArg(null);
        assertNull(field.getArg(0), "Null arg should not be added");
    }

    @Test
    public void testAddArgWithNullKey() {
        Arg arg = createArg(null, "validName", 0);
        field.addArg(arg);
        assertNull(field.getArg(0), "Arg with null key should not be added");
    }

    @Test
    public void testAddArgWithEmptyKey() {
        Arg arg = createArg("", "validName", 0);
        field.addArg(arg);
        assertNull(field.getArg(0), "Arg with empty key should not be added");
    }

    @Test
    public void testAddArgWithNullName() {
        Arg arg = createArg("validKey", null, 0);
        field.addArg(arg);
        assertEquals(arg, field.getArg(0), "Arg with null name should be added correctly");
    }

    @Test
    public void testAddArgWithDifferentPositions() {
        Arg arg1 = createArg("key1", "name1", 0);
        Arg arg2 = createArg("key2", "name2", 1);
        field.addArg(arg1);
        field.addArg(arg2);
        assertEquals(arg1, field.getArg(0), "First arg should be added correctly");
        assertEquals(arg2, field.getArg(1), "Second arg should be added correctly");
    }

    @Test
    public void testAddArgWithSamePosition() {
        Arg arg1 = createArg("key1", "name1", 0);
        Arg arg2 = createArg("key2", "name2", 0);
        field.addArg(arg1);
        field.addArg(arg2);
        assertEquals(arg2, field.getArg(0), "Second arg should override the first arg");
    }
}
