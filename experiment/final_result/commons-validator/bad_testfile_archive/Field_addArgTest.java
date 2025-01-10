
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class Field_addArgTest {

    @Test
    void testAddArg_NullArg() {
        Field field = new Field();
        field.addArg(null);
        assertNull(field.args[0]);
    }

    @Test
    void testAddArg_NullKey() {
        Field field = new Field();
        Arg arg = new Arg();
        arg.setKey(null);
        field.addArg(arg);
        assertNull(field.args[0]);
    }

    @Test
    void testAddArg_EmptyKey() {
        Field field = new Field();
        Arg arg = new Arg();
        arg.setKey("");
        field.addArg(arg);
        assertNull(field.args[0]);
    }

    @Test
    void testAddArg_ValidArg() {
        Field field = new Field();
        Arg arg = new Arg();
        arg.setKey("key");
        arg.setName("name");
        field.addArg(arg);
        assertNotNull(field.args[0]);
        assertEquals(arg, field.args[0].get("name"));
    }

    @Test
    void testAddArg_DefaultArg() {
        Field field = new Field();
        Arg arg = new Arg();
        arg.setKey("key");
        field.addArg(arg);
        assertNotNull(field.args[0]);
        assertEquals(arg, field.args[0].get(Field.DEFAULT_ARG));
    }
}
