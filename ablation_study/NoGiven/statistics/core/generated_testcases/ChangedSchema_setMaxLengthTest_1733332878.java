
package org.openapitools.openapidiff.core.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedSchema_setMaxLengthTest {

    @Test
    void testSetMaxLength() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        ChangedMaxLength maxLength = new ChangedMaxLength(10, 20, null);

        // When
        changedSchema.setMaxLength(maxLength);

        // Then
        assertEquals(maxLength, changedSchema.getMaxLength());
        assertTrue(changedSchema.getChangedElements().contains(maxLength));
    }
}
