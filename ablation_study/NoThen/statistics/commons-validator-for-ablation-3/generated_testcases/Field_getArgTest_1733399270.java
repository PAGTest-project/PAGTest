
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

    private Arg createArg(String key, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setPosition(position);
        return arg;
    }

    @Test
    public void testGetArgWithValidPosition() {
        field.addArg(createArg("key1", 0));
        field.addArg(createArg("key2", 1));

        assertEquals("key1", field.getArg("key1", 0).getKey());
        assertEquals("key2", field.getArg("key2", 1).getKey());
    }

    @Test
    public void testGetArgWithInvalidPosition() {
        field.addArg(createArg("key1", 0));

        assertNull(field.getArg("key1", 1));
    }

    @Test
    public void testGetArgWithDefaultArg() {
        field.addArg(createArg(Field.DEFAULT_ARG, 0));

        assertNull(field.getArg(Field.DEFAULT_ARG, 0));
    }

    @Test
    public void testGetArgWithNullArg() {
        field.addArg(createArg("key1", 0));

        assertNull(field.getArg("nonExistentKey", 0));
    }
}
