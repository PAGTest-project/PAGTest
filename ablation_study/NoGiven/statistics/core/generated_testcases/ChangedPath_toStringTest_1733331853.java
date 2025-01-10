
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedPath_toStringTest {

    @Test
    public void testToString() {
        String pathUrl = "/test";
        PathItem oldPath = new PathItem();
        PathItem newPath = new PathItem();
        DiffContext context = new DiffContext();
        Map<PathItem.HttpMethod, Operation> increased = new LinkedHashMap<>();
        Map<PathItem.HttpMethod, Operation> missing = new LinkedHashMap<>();
        List<ChangedOperation> changed = new ArrayList<>();
        ChangedExtensions extensions = new ChangedExtensions();

        ChangedPath changedPath = new ChangedPath(pathUrl, oldPath, newPath, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed)
                .setExtensions(extensions);

        String expected = "ChangedPath(pathUrl=" + pathUrl + ", oldPath=" + oldPath + ", newPath=" + newPath + ", context=" + context + ", increased=" + increased + ", missing=" + missing + ", changed=" + changed + ", extensions=" + extensions + ")";
        assertEquals(expected, changedPath.toString());
    }
}
