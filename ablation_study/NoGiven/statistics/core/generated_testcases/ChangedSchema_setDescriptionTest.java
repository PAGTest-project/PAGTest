
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ChangedSchema_setDescriptionTest {

    @Test
    public void testSetDescription() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        ChangedMetadata description = new ChangedMetadata();
        description.setLeft("Old Description");
        description.setRight("New Description");

        // When
        ChangedSchema result = changedSchema.setDescription(description);

        // Then
        assertNotNull(result);
        assertEquals(description, result.getDescription());
    }
}
