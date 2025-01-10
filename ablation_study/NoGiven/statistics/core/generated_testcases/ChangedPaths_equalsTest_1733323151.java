
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangedPaths_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ChangedPaths changedPaths = new ChangedPaths(new HashMap<>(), new HashMap<>(), new OpenApiDiffOptions());
        assertTrue(changedPaths.equals(changedPaths));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedPaths changedPaths = new ChangedPaths(new HashMap<>(), new HashMap<>(), new OpenApiDiffOptions());
        assertFalse(changedPaths.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedPaths changedPaths = new ChangedPaths(new HashMap<>(), new HashMap<>(), new OpenApiDiffOptions());
        assertFalse(changedPaths.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentFields() {
        Map<String, PathItem> oldPathMap = new HashMap<>();
        Map<String, PathItem> newPathMap = new HashMap<>();
        Map<String, PathItem> increased = new HashMap<>();
        Map<String, PathItem> missing = new HashMap<>();
        Map<String, ChangedPath> changed = new HashMap<>();

        ChangedPaths changedPaths1 = new ChangedPaths(oldPathMap, newPathMap, new OpenApiDiffOptions());
        changedPaths1.setIncreased(increased);
        changedPaths1.setMissing(missing);
        changedPaths1.setChanged(changed);

        ChangedPaths changedPaths2 = new ChangedPaths(oldPathMap, newPathMap, new OpenApiDiffOptions());
        changedPaths2.setIncreased(new HashMap<>(increased));
        changedPaths2.setMissing(new HashMap<>(missing));
        changedPaths2.setChanged(new HashMap<>(changed));

        assertTrue(changedPaths1.equals(changedPaths2));
    }

    @Test
    public void testEquals_DifferentIncreased() {
        Map<String, PathItem> oldPathMap = new HashMap<>();
        Map<String, PathItem> newPathMap = new HashMap<>();
        Map<String, PathItem> increased1 = new HashMap<>();
        Map<String, PathItem> increased2 = new HashMap<>();
        Map<String, PathItem> missing = new HashMap<>();
        Map<String, ChangedPath> changed = new HashMap<>();

        increased1.put("path1", new PathItem());
        increased2.put("path2", new PathItem());

        ChangedPaths changedPaths1 = new ChangedPaths(oldPathMap, newPathMap, new OpenApiDiffOptions());
        changedPaths1.setIncreased(increased1);
        changedPaths1.setMissing(missing);
        changedPaths1.setChanged(changed);

        ChangedPaths changedPaths2 = new ChangedPaths(oldPathMap, newPathMap, new OpenApiDiffOptions());
        changedPaths2.setIncreased(increased2);
        changedPaths2.setMissing(missing);
        changedPaths2.setChanged(changed);

        assertFalse(changedPaths1.equals(changedPaths2));
    }
}
