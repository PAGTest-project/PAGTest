
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Msg;
import org.apache.commons.validator.Var;
import org.junit.jupiter.api.Test;

public class ValidatorUtils_copyMapTest {

    @Test
    public void testCopyMapWithMsg() {
        Map<String, Object> original = new HashMap<>();
        Msg msg = new Msg();
        msg.setName("testMsg");
        original.put("msgKey", msg);

        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertTrue(copy.containsKey("msgKey"));
        assertTrue(copy.get("msgKey") instanceof Msg);
        assertEquals(msg.getName(), ((Msg) copy.get("msgKey")).getName());
    }

    @Test
    public void testCopyMapWithArg() {
        Map<String, Object> original = new HashMap<>();
        Arg arg = new Arg();
        arg.setName("testArg");
        original.put("argKey", arg);

        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertTrue(copy.containsKey("argKey"));
        assertTrue(copy.get("argKey") instanceof Arg);
        assertEquals(arg.getName(), ((Arg) copy.get("argKey")).getName());
    }

    @Test
    public void testCopyMapWithVar() {
        Map<String, Object> original = new HashMap<>();
        Var var = new Var();
        var.setName("testVar");
        original.put("varKey", var);

        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertTrue(copy.containsKey("varKey"));
        assertTrue(copy.get("varKey") instanceof Var);
        assertEquals(var.getName(), ((Var) copy.get("varKey")).getName());
    }

    @Test
    public void testCopyMapWithOtherTypes() {
        Map<String, Object> original = new HashMap<>();
        original.put("stringKey", "stringValue");
        original.put("intKey", 123);

        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertEquals("stringValue", copy.get("stringKey"));
        assertEquals(123, copy.get("intKey"));
    }
}
