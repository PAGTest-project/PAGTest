
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ChangedSchema_setItemsTest {

    @Test
    public void testSetItems() {
        // Given
        ChangedSchema changedSchema = new ChangedSchema();
        ChangedSchema newItems = new ChangedSchema();

        // When
        ChangedSchema result = changedSchema.setItems(newItems);

        // Then
        assertEquals(newItems, result.getItems());
    }
}
