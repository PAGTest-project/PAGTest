
package org.openapitools.openapidiff.core.model;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedExtensions_getChangedElementsTest {

    @Test
    public void testGetChangedElements() {
        // Given
        Map<String, Object> oldExtensions = new HashMap<>();
        Map<String, Object> newExtensions = new HashMap<>();
        DiffContext context = new DiffContext();
        ChangedExtensions changedExtensions = new ChangedExtensions(oldExtensions, newExtensions, context);

        Map<String, Changed> increased = new HashMap<>();
        increased.put("key1", new Changed());
        increased.put("key2", new Changed());

        Map<String, Changed> missing = new HashMap<>();
        missing.put("key3", new Changed());

        Map<String, Changed> changed = new HashMap<>();
        changed.put("key4", new Changed());

        changedExtensions.setIncreased(increased);
        changedExtensions.setMissing(missing);
        changedExtensions.setChanged(changed);

        // When
        List<Changed> result = changedExtensions.getChangedElements();

        // Then
        assertEquals(4, result.size());
    }
}
