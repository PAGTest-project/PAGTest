
package org.openapitools.openapidiff.core.compare;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheKey_toStringTest {

    @Test
    public void testToString() {
        String left = "leftValue";
        String right = "rightValue";
        DiffContext context = new DiffContext();
        CacheKey cacheKey = new CacheKey(left, right, context);

        String expected = "CacheKey(left=leftValue, right=rightValue, context=" + context + ")";
        assertEquals(expected, cacheKey.toString());
    }
}
