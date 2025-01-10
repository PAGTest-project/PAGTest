
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.Closure;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClosureUtils_switchMapClosureTest {

    @Test
    public void testSwitchMapClosure_WithDefaultClosure() {
        // Given
        Map<String, Closure<String>> map = new HashMap<>();
        Closure<String> closure1 = obj -> { obj + " processed by closure1"; };
        Closure<String> closure2 = obj -> { obj + " processed by closure2"; };
        Closure<String> defaultClosure = obj -> { obj + " processed by default closure"; };
        map.put("key1", closure1);
        map.put("key2", closure2);
        map.put(null, defaultClosure);

        // When
        Closure<String> resultClosure = ClosureUtils.switchMapClosure(map);

        // Then
        assertEquals("test processed by closure1", resultClosure.execute("test"));
        assertEquals("test processed by closure2", resultClosure.execute("test"));
        assertEquals("test processed by default closure", resultClosure.execute("test"));
    }

    @Test
    public void testSwitchMapClosure_WithNullMap() {
        // Given
        Map<String, Closure<String>> map = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> ClosureUtils.switchMapClosure(map));
    }
}
