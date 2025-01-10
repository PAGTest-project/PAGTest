
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.apache.commons.configuration2.CompositeConfiguration;

public class ChangedSchema_setDiscriminatorPropertyChangedTest {

    @Test
    public void testSetDiscriminatorPropertyChanged() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        changedSchema.setContext(new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));
        changedSchema.setOldSchema(new Schema<>());
        changedSchema.setNewSchema(new Schema<>());

        // When
        ChangedSchema result = changedSchema.setDiscriminatorPropertyChanged(true);

        // Then
        assertTrue(result.isDiscriminatorPropertyChanged());
    }

    @Test
    public void testSetDiscriminatorPropertyChangedToFalse() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        changedSchema.setContext(new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));
        changedSchema.setOldSchema(new Schema<>());
        changedSchema.setNewSchema(new Schema<>());

        // When
        ChangedSchema result = changedSchema.setDiscriminatorPropertyChanged(false);

        // Then
        assertFalse(result.isDiscriminatorPropertyChanged());
    }
}
