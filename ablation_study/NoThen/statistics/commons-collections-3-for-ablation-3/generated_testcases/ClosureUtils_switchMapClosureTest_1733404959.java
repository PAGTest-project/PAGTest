
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.Closure;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosureUtils_switchMapClosureTest {

    @Test
    public void testSwitchMapClosure_WithNonNullMap() {
        Map<String, Closure<String>> map = new HashMap<>();
        map.put("key1", s -> assertEquals("value1", s));
        map.put("key2", s -> assertEquals("value2", s));
        map.put(null, s -> assertEquals("default", s));

        Closure<String> closure = ClosureUtils.switchMapClosure(map);

        closure.execute("value1");
        closure.execute("value2");
        closure.execute("default");
    }

    @Test
    public void testSwitchMapClosure_WithNullMap() {
        assertThrows(NullPointerException.class, () -> {
            ClosureUtils.switchMapClosure(null);
        });
    }
}
