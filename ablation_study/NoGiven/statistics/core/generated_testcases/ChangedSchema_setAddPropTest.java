
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class ChangedSchema_setAddPropTest {

    @Test
    public void testSetAddProp() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        ChangedSchema addProp = new ChangedSchema();

        // When
        ChangedSchema result = changedSchema.setAddProp(addProp);

        // Then
        assertNotNull(result);
        assertEquals(addProp, changedSchema.getAddProp());
    }
}
