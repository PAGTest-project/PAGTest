
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class ChangedSchema_setChangeDeprecatedTest {

    @Test
    public void testSetChangeDeprecated() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();

        // When
        ChangedSchema result = changedSchema.setChangeDeprecated(true);

        // Then
        assertNotNull(result);
        assertEquals(true, changedSchema.isChangeDeprecated());
    }
}
