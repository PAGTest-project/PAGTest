
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChangedSchema_setChangedTypeTest {

    private ChangedSchema changedSchema;

    @BeforeEach
    public void setUp() {
        changedSchema = new ChangedSchema();
    }

    @Test
    public void testSetChangedType() {
        // Given
        boolean changedType = true;

        // When
        ChangedSchema result = changedSchema.setChangedType(changedType);

        // Then
        assertNotNull(result);
        assertEquals(changedType, changedSchema.isChangedType());
    }
}
