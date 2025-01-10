
package org.openapitools.openapidiff.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.ChangedSchema;
import org.openapitools.openapidiff.core.model.ChangedExtensions;
import org.openapitools.openapidiff.core.model.Changed;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_getChangedElementsTest {

    private ChangedOpenApi changedOpenApi;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions(new org.apache.commons.configuration2.CompositeConfiguration());
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testGetChangedElements_NoChanges() {
        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedExtensions(null);
        changedOpenApi.setChangedSchemas(Collections.emptyList());

        List<Changed> result = changedOpenApi.getChangedElements();
        assertEquals(0, result.size());
    }

    @Test
    public void testGetChangedElements_WithChanges() {
        ChangedOperation changedOperation = new ChangedOperation("path", null, null, null);
        ChangedExtensions changedExtensions = new ChangedExtensions(null, null, null);
        ChangedSchema changedSchema = new ChangedSchema();

        changedOpenApi.setChangedOperations(Arrays.asList(changedOperation));
        changedOpenApi.setChangedExtensions(changedExtensions);
        changedOpenApi.setChangedSchemas(Arrays.asList(changedSchema));

        List<Changed> result = changedOpenApi.getChangedElements();
        assertEquals(3, result.size());
    }

    @Test
    public void testGetChangedElements_OnlyOperations() {
        ChangedOperation changedOperation = new ChangedOperation("path", null, null, null);

        changedOpenApi.setChangedOperations(Arrays.asList(changedOperation));
        changedOpenApi.setChangedExtensions(null);
        changedOpenApi.setChangedSchemas(Collections.emptyList());

        List<Changed> result = changedOpenApi.getChangedElements();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetChangedElements_OnlyExtensions() {
        ChangedExtensions changedExtensions = new ChangedExtensions(null, null, null);

        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedExtensions(changedExtensions);
        changedOpenApi.setChangedSchemas(Collections.emptyList());

        List<Changed> result = changedOpenApi.getChangedElements();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetChangedElements_OnlySchemas() {
        ChangedSchema changedSchema = new ChangedSchema();

        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedExtensions(null);
        changedOpenApi.setChangedSchemas(Arrays.asList(changedSchema));

        List<Changed> result = changedOpenApi.getChangedElements();
        assertEquals(1, result.size());
    }
}
