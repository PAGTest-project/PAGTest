
package org.apache.commons.validator;

import org.apache.commons.collections.FastHashMap;
import org.apache.commons.validator.util.ValidatorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class Field_cloneTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
        field.hVars = new FastHashMap();
        field.hMsgs = new FastHashMap();
        field.args = new Map[1];
        field.args[0] = new HashMap<>();
    }

    @Test
    public void testCloneWithValidArgs() {
        Map<String, Arg> argMap = new HashMap<>();
        Arg arg = new Arg();
        arg.setName("testArg");
        argMap.put("testValidator", arg);
        field.args[0] = argMap;

        Field clonedField = (Field) field.clone();

        assertNotNull(clonedField);
        assertNotSame(field, clonedField);
        assertEquals(field.args.length, clonedField.args.length);
        assertEquals(field.args[0].size(), clonedField.args[0].size());
        assertEquals(field.args[0].get("testValidator").getName(), clonedField.args[0].get("testValidator").getName());
    }

    @Test
    public void testCloneWithNullArgs() {
        field.args[0] = null;

        Field clonedField = (Field) field.clone();

        assertNotNull(clonedField);
        assertNotSame(field, clonedField);
        assertEquals(field.args.length, clonedField.args.length);
        assertNull(clonedField.args[0]);
    }

    @Test
    public void testCloneWithEmptyArgs() {
        field.args = new Map[0];

        Field clonedField = (Field) field.clone();

        assertNotNull(clonedField);
        assertNotSame(field, clonedField);
        assertEquals(field.args.length, clonedField.args.length);
    }

    @Test
    public void testCloneWithFastHashMap() {
        field.hVars.put("var1", "value1");
        field.hMsgs.put("msg1", "message1");

        Field clonedField = (Field) field.clone();

        assertNotNull(clonedField);
        assertNotSame(field, clonedField);
        assertEquals(field.hVars.size(), clonedField.hVars.size());
        assertEquals(field.hMsgs.size(), clonedField.hMsgs.size());
        assertEquals(field.hVars.get("var1"), clonedField.hVars.get("var1"));
        assertEquals(field.hMsgs.get("msg1"), clonedField.hMsgs.get("msg1"));
    }
}
