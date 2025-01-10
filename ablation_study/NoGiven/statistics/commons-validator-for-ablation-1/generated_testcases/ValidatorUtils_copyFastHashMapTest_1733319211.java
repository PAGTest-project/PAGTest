
package org.apache.commons.validator.util;

import org.apache.commons.collections.FastHashMap;
import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Msg;
import org.apache.commons.validator.Var;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorUtils_copyFastHashMapTest {

    @Test
    public void testCopyFastHashMap() {
        FastHashMap original = new FastHashMap();
        original.put("key1", new Msg());
        original.put("key2", new Arg());
        original.put("key3", new Var());
        original.put("key4", "value4");

        FastHashMap copied = ValidatorUtils.copyFastHashMap(original);

        assertEquals(original.size(), copied.size());
        assertTrue(copied.get("key1") instanceof Msg);
        assertTrue(copied.get("key2") instanceof Arg);
        assertTrue(copied.get("key3") instanceof Var);
        assertEquals("value4", copied.get("key4"));
        assertEquals(original.getFast(), copied.getFast());
    }
}
