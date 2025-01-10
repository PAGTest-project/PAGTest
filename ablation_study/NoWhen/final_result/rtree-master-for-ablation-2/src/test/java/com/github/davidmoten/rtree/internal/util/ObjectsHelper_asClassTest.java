
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
    public void testAsClassWithIncorrectClass() {
        Object obj = new Object();
        Optional<String> result = ObjectsHelper.asClass(obj, String.class);
        assertFalse(result.isPresent());
    }

    @Test
    public void testAsClassWithCorrectClass() {
        String str = "test";
        Optional<String> result = ObjectsHelper.asClass(str, String.class);
        assertTrue(result.isPresent());
        assertEquals(str, result.get());
    }

    @Test
    public void coverPrivateConstructor() {
        ObjectsHelper.instantiateForTestCoveragePurposesOnly();
    }
}
