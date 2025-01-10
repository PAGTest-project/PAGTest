
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.Closure;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClosureUtils_switchMapClosureTest {

    @Test
    public void testSwitchMapClosure_NormalCase() {
        Map<String, Closure<String>> map = new HashMap<>();
        map.put("key1", obj -> {});
        map.put("key2", obj -> {});
        map.put(null, obj -> {});

        Closure<String> result = ClosureUtils.switchMapClosure(map);
        assertTrue(result instanceof Closure);
    }

    @Test
    public void testSwitchMapClosure_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            ClosureUtils.switchMapClosure(null);
        });
    }
}
