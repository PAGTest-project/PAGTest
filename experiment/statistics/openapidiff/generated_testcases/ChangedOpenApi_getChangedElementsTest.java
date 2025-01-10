
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_getChangedElementsTest {

    private ChangedOpenApi changedOpenApi;
    private List<ChangedOperation> changedOperations;
    private List<ChangedSchema> changedSchemas;
    private ChangedExtensions changedExtensions;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = mock(OpenApiDiffOptions.class);
        changedOpenApi = new ChangedOpenApi(options);

        changedOperations = Arrays.asList(mock(ChangedOperation.class), mock(ChangedOperation.class));
        changedSchemas = Arrays.asList(mock(ChangedSchema.class), mock(ChangedSchema.class));
        changedExtensions = mock(ChangedExtensions.class);

        changedOpenApi.setChangedOperations(changedOperations);
        changedOpenApi.setChangedSchemas(changedSchemas);
        changedOpenApi.setChangedExtensions(changedExtensions);
    }

    @Test
    public void testGetChangedElements() {
        List<Changed> changedElements = changedOpenApi.getChangedElements();

        assertEquals(changedOperations.size() + changedSchemas.size() + 1, changedElements.size());
        assertEquals(changedOperations, changedElements.subList(0, changedOperations.size()));
        assertEquals(changedExtensions, changedElements.get(changedOperations.size()));
        assertEquals(changedSchemas, changedElements.subList(changedOperations.size() + 1, changedElements.size()));
    }
}
