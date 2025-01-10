
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
        original.put("key1", msg);
        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertTrue(copy.containsKey("key1"));
        assertTrue(copy.get("key1") instanceof Msg);
        assertEquals(msg.toString(), ((Msg) copy.get("key1")).toString());
    }

    @Test
    public void testCopyMapWithArg() {
        Map<String, Object> original = new HashMap<>();
        Arg arg = new Arg();
        original.put("key2", arg);
        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertTrue(copy.containsKey("key2"));
        assertTrue(copy.get("key2") instanceof Arg);
        assertEquals(arg.toString(), ((Arg) copy.get("key2")).toString());
    }

    @Test
    public void testCopyMapWithVar() {
        Map<String, Object> original = new HashMap<>();
        Var var = new Var();
        original.put("key3", var);
        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertTrue(copy.containsKey("key3"));
        assertTrue(copy.get("key3") instanceof Var);
        assertEquals(var.toString(), ((Var) copy.get("key3")).toString());
    }

    @Test
    public void testCopyMapWithOtherType() {
        Map<String, Object> original = new HashMap<>();
        original.put("key4", "value4");
        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertTrue(copy.containsKey("key4"));
        assertEquals("value4", copy.get("key4"));
    }

    @Test
    public void testCopyMapEmpty() {
        Map<String, Object> original = new HashMap<>();
        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertTrue(copy.isEmpty());
    }
}
