
package org.openapitools.openapidiff.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_getChangedElementsTest {

    private ChangedOpenApi changedOpenApi;
    private ChangedOperation changedOperation1;
    private ChangedOperation changedOperation2;
    private ChangedSchema changedSchema1;
    private ChangedSchema changedSchema2;
    private ChangedExtensions changedExtensions;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        changedOpenApi = new ChangedOpenApi(options);
        changedOperation1 = new ChangedOperation();
        changedOperation2 = new ChangedOperation();
        changedSchema1 = new ChangedSchema();
        changedSchema2 = new ChangedSchema();
        changedExtensions = new ChangedExtensions(Collections.emptyMap(), Collections.emptyMap(), null);
    }

    @Test
    public void testGetChangedElementsWithOperationsAndSchemas() {
        List<ChangedOperation> changedOperations = Arrays.asList(changedOperation1, changedOperation2);
        List<ChangedSchema> changedSchemas = Arrays.asList(changedSchema1, changedSchema2);

        changedOpenApi.setChangedOperations(changedOperations);
        changedOpenApi.setChangedSchemas(changedSchemas);
        changedOpenApi.setChangedExtensions(changedExtensions);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertEquals(4, changedElements.size());
        assertEquals(changedOperation1, changedElements.get(0));
        assertEquals(changedOperation2, changedElements.get(1));
        assertEquals(changedExtensions, changedElements.get(2));
        assertEquals(changedSchema1, changedElements.get(3));
    }

    @Test
    public void testGetChangedElementsWithNoOperationsAndSchemas() {
        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedSchemas(Collections.emptyList());
        changedOpenApi.setChangedExtensions(changedExtensions);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertEquals(1, changedElements.size());
        assertEquals(changedExtensions, changedElements.get(0));
    }

    @Test
    public void testGetChangedElementsWithOnlyOperations() {
        List<ChangedOperation> changedOperations = Arrays.asList(changedOperation1, changedOperation2);

        changedOpenApi.setChangedOperations(changedOperations);
        changedOpenApi.setChangedSchemas(Collections.emptyList());
        changedOpenApi.setChangedExtensions(changedExtensions);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertEquals(3, changedElements.size());
        assertEquals(changedOperation1, changedElements.get(0));
        assertEquals(changedOperation2, changedElements.get(1));
        assertEquals(changedExtensions, changedElements.get(2));
    }

    @Test
    public void testGetChangedElementsWithOnlySchemas() {
        List<ChangedSchema> changedSchemas = Arrays.asList(changedSchema1, changedSchema2);

        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedSchemas(changedSchemas);
        changedOpenApi.setChangedExtensions(changedExtensions);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertEquals(3, changedElements.size());
        assertEquals(changedExtensions, changedElements.get(0));
        assertEquals(changedSchema1, changedElements.get(1));
        assertEquals(changedSchema2, changedElements.get(2));
    }
}
