
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
        Msg msg = new Msg();
        Arg arg = new Arg();
        Var var = new Var();
        Object obj = new Object();

        original.put("msgKey", msg);
        original.put("argKey", arg);
        original.put("varKey", var);
        original.put("objKey", obj);

        FastHashMap copied = ValidatorUtils.copyFastHashMap(original);

        assertEquals(4, copied.size());
        assertTrue(copied.get("msgKey") instanceof Msg);
        assertTrue(copied.get("argKey") instanceof Arg);
        assertTrue(copied.get("varKey") instanceof Var);
        assertSame(obj, copied.get("objKey"));
    }
}
