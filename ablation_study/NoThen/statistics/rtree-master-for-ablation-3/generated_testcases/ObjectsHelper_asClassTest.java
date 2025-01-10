
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.Optional;

public class ObjectsHelper_asClassTest {

    @Test
    public void testAsClassWithNullObject() {
        Optional<String> result = ObjectsHelper.asClass(null, String.class);
        assertFalse(result.isPresent());
    }

    @Test
    public void testAsClassWithMatchingClass() {
        String obj = "test";
        Optional<String> result = ObjectsHelper.asClass(obj, String.class);
        assertTrue(result.isPresent());
        assertEquals(obj, result.get());
    }

    @Test
    public void testAsClassWithNonMatchingClass() {
        Integer obj = 123;
        Optional<String> result = ObjectsHelper.asClass(obj, String.class);
        assertFalse(result.isPresent());
    }

    @Test
    public void coverPrivateConstructor() {
        ObjectsHelper.instantiateForTestCoveragePurposesOnly();
    }
}
