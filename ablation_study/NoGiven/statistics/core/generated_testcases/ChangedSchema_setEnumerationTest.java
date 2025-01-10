
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.schema.ChangedEnum;

public class ChangedSchema_setEnumerationTest {

    @Test
    public void testSetEnumeration() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        ChangedEnum<?> enumeration = new ChangedEnum<>(null, null, null);

        // When
        changedSchema.setEnumeration(enumeration);

        // Then
        assertNotNull(changedSchema.getEnumeration());
        assertEquals(enumeration, changedSchema.getEnumeration());
    }
}
