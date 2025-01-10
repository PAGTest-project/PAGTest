
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import io.swagger.v3.oas.models.PathItem;

public class ChangedPaths_toStringTest {

    @Test
    public void testToString() {
        Map<String, PathItem> oldPathMap = new HashMap<>();
        Map<String, PathItem> newPathMap = new HashMap<>();
        Map<String, PathItem> increased = new HashMap<>();
        Map<String, PathItem> missing = new HashMap<>();
        Map<String, ChangedPath> changed = new HashMap<>();

        ChangedPaths changedPaths = new ChangedPaths(oldPathMap, newPathMap, new OpenApiDiffOptions());
        changedPaths.setIncreased(increased);
        changedPaths.setMissing(missing);
        changedPaths.setChanged(changed);

        String expected = "ChangedPaths(oldPathMap={}, newPathMap={}, increased={}, missing={}, changed={})";
        assertEquals(expected, changedPaths.toString());
    }
}
