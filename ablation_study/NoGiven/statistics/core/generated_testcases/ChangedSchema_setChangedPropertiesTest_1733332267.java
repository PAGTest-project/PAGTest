
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.schema.ChangedSchema;

public class ChangedSchema_setChangedPropertiesTest {

    @Test
    public void testSetChangedProperties() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        Map<String, ChangedSchema> changedProperties = new HashMap<>();
        changedProperties.put("prop1", new ChangedSchema());
        changedProperties.put("prop2", new ChangedSchema());

        // When
        ChangedSchema result = changedSchema.setChangedProperties(changedProperties);

        // Then
        assertNotNull(result);
        assertEquals(changedProperties, changedSchema.getChangedProperties());
    }
}
