
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;

public class ChangedMaxLength_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ChangedMaxLength obj = new ChangedMaxLength(10, 20, new DiffContext(null));
        assertTrue(obj.equals(obj));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedMaxLength obj = new ChangedMaxLength(10, 20, new DiffContext(null));
        assertFalse(obj.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedMaxLength obj = new ChangedMaxLength(10, 20, new DiffContext(null));
        assertFalse(obj.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentValues() {
        ChangedMaxLength obj1 = new ChangedMaxLength(10, 20, new DiffContext(null));
        ChangedMaxLength obj2 = new ChangedMaxLength(10, 30, new DiffContext(null));
        assertFalse(obj1.equals(obj2));
    }

    @Test
    public void testEquals_SameValues() {
        DiffContext context = new DiffContext(null);
        ChangedMaxLength obj1 = new ChangedMaxLength(10, 20, context);
        ChangedMaxLength obj2 = new ChangedMaxLength(10, 20, context);
        assertTrue(obj1.equals(obj2));
    }
}
