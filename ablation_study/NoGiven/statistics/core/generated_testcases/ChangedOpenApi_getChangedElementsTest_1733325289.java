
package org.openapitools.openapidiff.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.ChangedSchema;
import org.openapitools.openapidiff.core.model.ChangedExtensions;
import org.openapitools.openapidiff.core.model.Changed;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_getChangedElementsTest {

    private ChangedOpenApi changedOpenApi;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testGetChangedElementsWithAllFieldsSet() {
        ChangedOperation changedOperation1 = new ChangedOperation();
        ChangedOperation changedOperation2 = new ChangedOperation();
        ChangedSchema changedSchema1 = new ChangedSchema();
        ChangedSchema changedSchema2 = new ChangedSchema();
        ChangedExtensions changedExtensions = new ChangedExtensions(null, null, null);

        changedOpenApi.setChangedOperations(Arrays.asList(changedOperation1, changedOperation2));
        changedOpenApi.setChangedSchemas(Arrays.asList(changedSchema1, changedSchema2));
        changedOpenApi.setChangedExtensions(changedExtensions);

        List<Changed> expected = Arrays.asList(changedOperation1, changedOperation2, changedExtensions, changedSchema1, changedSchema2);
        List<Changed> actual = changedOpenApi.getChangedElements();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetChangedElementsWithEmptyLists() {
        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedSchemas(Collections.emptyList());
        changedOpenApi.setChangedExtensions(null);

        List<Changed> expected = Collections.emptyList();
        List<Changed> actual = changedOpenApi.getChangedElements();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetChangedElementsWithOnlyChangedOperations() {
        ChangedOperation changedOperation1 = new ChangedOperation();
        ChangedOperation changedOperation2 = new ChangedOperation();

        changedOpenApi.setChangedOperations(Arrays.asList(changedOperation1, changedOperation2));
        changedOpenApi.setChangedSchemas(Collections.emptyList());
        changedOpenApi.setChangedExtensions(null);

        List<Changed> expected = Arrays.asList(changedOperation1, changedOperation2);
        List<Changed> actual = changedOpenApi.getChangedElements();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetChangedElementsWithOnlyChangedSchemas() {
        ChangedSchema changedSchema1 = new ChangedSchema();
        ChangedSchema changedSchema2 = new ChangedSchema();

        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedSchemas(Arrays.asList(changedSchema1, changedSchema2));
        changedOpenApi.setChangedExtensions(null);

        List<Changed> expected = Arrays.asList(changedSchema1, changedSchema2);
        List<Changed> actual = changedOpenApi.getChangedElements();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetChangedElementsWithOnlyChangedExtensions() {
        ChangedExtensions changedExtensions = new ChangedExtensions(null, null, null);

        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedSchemas(Collections.emptyList());
        changedOpenApi.setChangedExtensions(changedExtensions);

        List<Changed> expected = Collections.singletonList(changedExtensions);
        List<Changed> actual = changedOpenApi.getChangedElements();

        assertEquals(expected, actual);
    }
}
