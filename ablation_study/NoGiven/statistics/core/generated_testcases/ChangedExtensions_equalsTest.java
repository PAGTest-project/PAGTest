
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedExtensions_equalsTest {

    @Test
    public void testEquals_SameObject() {
        ChangedExtensions ce = new ChangedExtensions(new HashMap<>(), new HashMap<>(), new DiffContext(null));
        assertTrue(ce.equals(ce));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedExtensions ce = new ChangedExtensions(new HashMap<>(), new HashMap<>(), new DiffContext(null));
        assertFalse(ce.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedExtensions ce = new ChangedExtensions(new HashMap<>(), new HashMap<>(), new DiffContext(null));
        assertFalse(ce.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentState() {
        Map<String, Object> oldExt1 = new HashMap<>();
        oldExt1.put("key1", "value1");
        Map<String, Object> newExt1 = new HashMap<>();
        newExt1.put("key2", "value2");
        DiffContext context1 = new DiffContext(null);

        Map<String, Object> oldExt2 = new HashMap<>();
        oldExt2.put("key1", "value1");
        Map<String, Object> newExt2 = new HashMap<>();
        newExt2.put("key2", "value2");
        DiffContext context2 = new DiffContext(null);

        ChangedExtensions ce1 = new ChangedExtensions(oldExt1, newExt1, context1);
        ChangedExtensions ce2 = new ChangedExtensions(oldExt2, newExt2, context2);

        assertTrue(ce1.equals(ce2));

        // Change one of the fields to make them different
        ce2.getOldExtensions().put("key3", "value3");
        assertFalse(ce1.equals(ce2));
    }
}
