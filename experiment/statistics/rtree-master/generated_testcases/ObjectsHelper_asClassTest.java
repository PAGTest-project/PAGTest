
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class ObjectsHelper_asClassTest {

    @Test
    public void testAsClassWithNullObject() {
        Optional<String> result = ObjectsHelper.asClass(null, String.class);
        assertFalse(result.isPresent());
    }

    @Test
    public void testAsClassWithIncorrectClass() {
        Optional<String> result = ObjectsHelper.asClass(123, String.class);
        assertFalse(result.isPresent());
    }

    @Test
    public void testAsClassWithCorrectClass() {
        Optional<String> result = ObjectsHelper.asClass("test", String.class);
        assertTrue(result.isPresent());
        assertEquals("test", result.get());
    }

    @Test
    public void coverPrivateConstructor() {
        ObjectsHelper.instantiateForTestCoveragePurposesOnly();
    }
}
