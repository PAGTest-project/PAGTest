
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

public class ChangedSchema_getChangedElementsTest {

    private ChangedSchema changedSchema;

    @BeforeEach
    public void setUp() {
        changedSchema = new ChangedSchema();
    }

    @Test
    public void testGetChangedElements_FirstCall() {
        // Given
        changedSchema.setContext(new DiffContext());

        // When
        List<Changed> result = changedSchema.getChangedElements();

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetChangedElements_SubsequentCall() {
        // Given
        changedSchema.setContext(new DiffContext());
        changedSchema.getChangedElements(); // First call to initialize changedElements

        // When
        List<Changed> result = changedSchema.getChangedElements();

        // Then
        assertEquals(Collections.emptyList(), result);
    }
}
