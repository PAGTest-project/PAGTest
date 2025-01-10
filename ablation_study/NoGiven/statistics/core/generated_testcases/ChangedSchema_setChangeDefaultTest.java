
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ChangedSchema_setChangeDefaultTest {

    @Test
    public void testSetChangeDefault() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();

        // When
        changedSchema.setChangeDefault(true);

        // Then
        assertTrue(changedSchema.isChangeDefault());
    }

    @Test
    public void testSetChangeDefaultToFalse() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();

        // When
        changedSchema.setChangeDefault(false);

        // Then
        assertFalse(changedSchema.isChangeDefault());
    }
}
