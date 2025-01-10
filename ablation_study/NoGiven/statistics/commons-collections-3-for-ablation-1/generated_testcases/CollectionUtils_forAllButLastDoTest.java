
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

class CollectionUtils_forAllButLastDoTest {

    @Test
    void testForAllButLastDoWithClosure() {
        Iterable<String> collection = Arrays.asList("a", "b", "c");
        Closure<String> closure = new Closure<String>() {
            @Override
            public void execute(String input) {
                // No-op
            }
        };

        Object result = CollectionUtils.forAllButLastDo(collection, closure);
        assertNotNull(result);
    }

    @Test
    void testForAllButLastDoWithoutClosure() {
        Iterable<String> collection = Arrays.asList("a", "b", "c");

        Object result = CollectionUtils.forAllButLastDo(collection, null);
        assertNull(result);
    }
}
