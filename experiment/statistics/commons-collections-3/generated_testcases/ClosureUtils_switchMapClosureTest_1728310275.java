
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.NOPClosure;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ClosureUtils_switchMapClosureTest {

    @Test
    public void testSwitchMapClosure_WithDefaultClosure() {
        // Given
        Map<String, Closure<String>> map = new HashMap<>();
        map.put("key1", new NOPClosure<>());
        map.put("key2", new NOPClosure<>());
        map.put(null, new NOPClosure<>());

        // When
        Closure<String> result = ClosureUtils.switchMapClosure(map);

        // Then
        assertNotNull(result);
        result.execute("key1");
        result.execute("key2");
        result.execute("unknownKey"); // Should execute the default closure
    }

    @Test
    public void testSwitchMapClosure_WithoutDefaultClosure() {
        // Given
        Map<String, Closure<String>> map = new HashMap<>();
        map.put("key1", new NOPClosure<>());
        map.put("key2", new NOPClosure<>());

        // When
        Closure<String> result = ClosureUtils.switchMapClosure(map);

        // Then
        assertNotNull(result);
        result.execute("key1");
        result.execute("key2");
        result.execute("unknownKey"); // Should not throw an exception
    }

    @Test
    public void testSwitchMapClosure_NullMap() {
        // Given
        Map<String, Closure<String>> map = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> ClosureUtils.switchMapClosure(map));
    }

    @Test
    public void testSwitchMapClosure_EmptyMap() {
        // Given
        Map<String, Closure<String>> map = new HashMap<>();

        // When
        Closure<String> result = ClosureUtils.switchMapClosure(map);

        // Then
        assertNotNull(result);
        result.execute("unknownKey"); // Should not throw an exception
    }
}
