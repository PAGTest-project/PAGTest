
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ChangedMetadata_isChangedTest {

    @Test
    public void testIsChanged_NoChanges() {
        ChangedMetadata metadata = new ChangedMetadata().setLeft("value").setRight("value");
        assertEquals(DiffResult.NO_CHANGES, metadata.isChanged());
    }

    @Test
    public void testIsChanged_Metadata() {
        ChangedMetadata metadata = new ChangedMetadata().setLeft("value1").setRight("value2");
        assertEquals(DiffResult.METADATA, metadata.isChanged());
    }
}
