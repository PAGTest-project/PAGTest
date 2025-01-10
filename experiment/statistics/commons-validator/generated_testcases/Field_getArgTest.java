
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
    public void testGetArg_ValidKey() {
        Arg arg = new Arg();
        arg.setKey("testKey");
        arg.setPosition(0);
        field.addArg(arg);

        Arg result = field.getArg("testKey", 0);
        assertEquals(arg, result);
    }

    @Test
    public void testGetArg_InvalidPosition() {
        Arg arg = new Arg();
        arg.setKey("testKey");
        arg.setPosition(0);
        field.addArg(arg);

        Arg result = field.getArg("testKey", 1);
        assertNull(result);
    }

    @Test
    public void testGetArg_NullArg() {
        Arg arg = new Arg();
        arg.setKey("testKey");
        arg.setPosition(0);
        field.addArg(arg);

        Arg result = field.getArg("nonexistentKey", 0);
        assertNull(result);
    }

    @Test
    public void testGetArg_DefaultArg() {
        Arg arg = new Arg();
        arg.setKey(Field.DEFAULT_ARG);
        arg.setPosition(0);
        field.addArg(arg);

        Arg result = field.getArg(Field.DEFAULT_ARG, 0);
        assertNull(result);
    }
}
