
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ChangedMetadata_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ChangedMetadata metadata = new ChangedMetadata().setLeft("left").setRight("right");
        assertTrue(metadata.equals(metadata));
    }

    @Test
    public void testEquals_NullOrDifferentClass() {
        ChangedMetadata metadata = new ChangedMetadata().setLeft("left").setRight("right");
        assertFalse(metadata.equals(null));
        assertFalse(metadata.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentValues() {
        ChangedMetadata metadata1 = new ChangedMetadata().setLeft("left1").setRight("right1");
        ChangedMetadata metadata2 = new ChangedMetadata().setLeft("left2").setRight("right2");
        assertFalse(metadata1.equals(metadata2));
    }

    @Test
    public void testEquals_SameValues() {
        ChangedMetadata metadata1 = new ChangedMetadata().setLeft("left").setRight("right");
        ChangedMetadata metadata2 = new ChangedMetadata().setLeft("left").setRight("right");
        assertTrue(metadata1.equals(metadata2));
        assertEquals(metadata1.hashCode(), metadata2.hashCode());
    }
}
