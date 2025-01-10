
package org.openapitools.openapidiff.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.ChangedSchema;
import org.openapitools.openapidiff.core.model.ChangedExtensions;
import org.openapitools.openapidiff.core.model.Endpoint;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChangedOpenApi_getChangedElementsTest {

    private ChangedOpenApi changedOpenApi;
    private OpenApiDiffOptions options;

    @BeforeEach
    public void setUp() {
        options = new OpenApiDiffOptions();
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testGetChangedElementsWithOperationsAndSchemas() {
        ChangedOperation changedOperation1 = new ChangedOperation();
        ChangedOperation changedOperation2 = new ChangedOperation();
        ChangedSchema changedSchema1 = new ChangedSchema();
        ChangedSchema changedSchema2 = new ChangedSchema();
        ChangedExtensions changedExtensions = new ChangedExtensions(null, null, null);

        changedOpenApi.setChangedOperations(Arrays.asList(changedOperation1, changedOperation2));
        changedOpenApi.setChangedSchemas(Arrays.asList(changedSchema1, changedSchema2));
        changedOpenApi.setChangedExtensions(changedExtensions);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertThat(changedElements).hasSize(5);
        assertThat(changedElements).containsExactly(changedOperation1, changedOperation2, changedExtensions, changedSchema1, changedSchema2);
    }

    @Test
    public void testGetChangedElementsWithNoChanges() {
        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedSchemas(Collections.emptyList());
        changedOpenApi.setChangedExtensions(null);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertThat(changedElements).isEmpty();
    }

    @Test
    public void testGetChangedElementsWithOnlyOperations() {
        ChangedOperation changedOperation1 = new ChangedOperation();
        ChangedOperation changedOperation2 = new ChangedOperation();

        changedOpenApi.setChangedOperations(Arrays.asList(changedOperation1, changedOperation2));
        changedOpenApi.setChangedSchemas(Collections.emptyList());
        changedOpenApi.setChangedExtensions(null);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertThat(changedElements).hasSize(2);
        assertThat(changedElements).containsExactly(changedOperation1, changedOperation2);
    }

    @Test
    public void testGetChangedElementsWithOnlySchemas() {
        ChangedSchema changedSchema1 = new ChangedSchema();
        ChangedSchema changedSchema2 = new ChangedSchema();

        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedSchemas(Arrays.asList(changedSchema1, changedSchema2));
        changedOpenApi.setChangedExtensions(null);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertThat(changedElements).hasSize(2);
        assertThat(changedElements).containsExactly(changedSchema1, changedSchema2);
    }

    @Test
    public void testGetChangedElementsWithOnlyExtensions() {
        ChangedExtensions changedExtensions = new ChangedExtensions(null, null, null);

        changedOpenApi.setChangedOperations(Collections.emptyList());
        changedOpenApi.setChangedSchemas(Collections.emptyList());
        changedOpenApi.setChangedExtensions(changedExtensions);

        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertThat(changedElements).hasSize(1);
        assertThat(changedElements).containsExactly(changedExtensions);
    }
}
