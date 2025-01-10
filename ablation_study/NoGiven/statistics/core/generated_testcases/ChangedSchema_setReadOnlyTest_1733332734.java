
package org.openapitools.openapidiff.core.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedSchema_setReadOnlyTest {

    @Test
    void testSetReadOnly() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        ChangedReadOnly readOnly = new ChangedReadOnly(false, true, null);

        // When
        changedSchema.setReadOnly(readOnly);

        // Then
        assertEquals(readOnly, changedSchema.getReadOnly());
    }
}
