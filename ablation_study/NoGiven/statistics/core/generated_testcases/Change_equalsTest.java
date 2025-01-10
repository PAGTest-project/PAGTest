
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Change_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        Change<String> change = Change.changed("old", "new");
        assertTrue(change.equals(change));
    }

    @Test
    public void testEquals_NullObject() {
        Change<String> change = Change.changed("old", "new");
        assertFalse(change.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        Change<String> change = Change.changed("old", "new");
        assertFalse(change.equals("not a Change object"));
    }

    @Test
    public void testEquals_DifferentValues() {
        Change<String> change1 = Change.changed("old", "new");
        Change<String> change2 = Change.changed("old", "new2");
        assertFalse(change1.equals(change2));
    }

    @Test
    public void testEquals_SameValues() {
        Change<String> change1 = Change.changed("old", "new");
        Change<String> change2 = Change.changed("old", "new");
        assertTrue(change1.equals(change2));
    }

    @Test
    public void testEquals_DifferentTypes() {
        Change<String> change1 = Change.changed("old", "new");
        Change<String> change2 = Change.added("new");
        assertFalse(change1.equals(change2));
    }
}
