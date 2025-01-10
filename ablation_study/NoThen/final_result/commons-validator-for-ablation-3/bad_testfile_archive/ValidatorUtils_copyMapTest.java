
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

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
        assertEquals(original, copy);
        assertNotSame(msg, copy.get("key1"));
    }

    @Test
    public void testCopyMapWithArg() {
        Map<String, Object> original = new HashMap<>();
        Arg arg = new Arg();
        original.put("key2", arg);
        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertEquals(original, copy);
        assertNotSame(arg, copy.get("key2"));
    }

    @Test
    public void testCopyMapWithVar() {
        Map<String, Object> original = new HashMap<>();
        Var var = new Var();
        original.put("key3", var);
        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertEquals(original, copy);
        assertNotSame(var, copy.get("key3"));
    }

    @Test
    public void testCopyMapWithOtherTypes() {
        Map<String, Object> original = new HashMap<>();
        original.put("key4", "value4");
        original.put("key5", 5);
        Map<String, Object> copy = ValidatorUtils.copyMap(original);
        assertEquals(original, copy);
        assertEquals(original.get("key4"), copy.get("key4"));
        assertEquals(original.get("key5"), copy.get("key5"));
    }
}
