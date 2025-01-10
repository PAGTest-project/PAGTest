
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.schema.ChangedWriteOnly;

public class ChangedSchema_setWriteOnlyTest {

    @Test
    public void testSetWriteOnly() {
        // Given
        ChangedSchema schema = new ChangedSchema();
        ChangedWriteOnly writeOnly = new ChangedWriteOnly(false, true, null);

        // When
        schema.setWriteOnly(writeOnly);

        // Then
        assertEquals(writeOnly, schema.getWriteOnly());
    }
}
