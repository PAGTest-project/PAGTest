
package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.OpenApiDiffOptions;
import org.apache.commons.configuration2.CompositeConfiguration;

public class CacheKey_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        CacheKey cacheKey = new CacheKey("left", "right", new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));
        assertTrue(cacheKey.equals(cacheKey));
    }

    @Test
    public void testEquals_NullObject() {
        CacheKey cacheKey = new CacheKey("left", "right", new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));
        assertFalse(cacheKey.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        CacheKey cacheKey = new CacheKey("left", "right", new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));
        assertFalse(cacheKey.equals("not a CacheKey"));
    }

    @Test
    public void testEquals_DifferentState() {
        CacheKey cacheKey1 = new CacheKey("left1", "right1", new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));
        CacheKey cacheKey2 = new CacheKey("left2", "right2", new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));
        assertFalse(cacheKey1.equals(cacheKey2));
    }

    @Test
    public void testEquals_SameState() {
        CompositeConfiguration config = new CompositeConfiguration();
        DiffContext context = new DiffContext(new OpenApiDiffOptions(config));
        CacheKey cacheKey1 = new CacheKey("left", "right", context);
        CacheKey cacheKey2 = new CacheKey("left", "right", context);
        assertTrue(cacheKey1.equals(cacheKey2));
    }
}
