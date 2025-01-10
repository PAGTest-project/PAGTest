
package org.openapitools.openapidiff.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_setChangedOperationsTest {

    private ChangedOpenApi changedOpenApi;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = OpenApiDiffOptions.fromConfig(new org.apache.commons.configuration2.CompositeConfiguration());
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testSetChangedOperations() {
        List<ChangedOperation> changedOperations = Collections.singletonList(new ChangedOperation(
                "/example",
                io.swagger.v3.oas.models.PathItem.HttpMethod.GET,
                null,
                null
        ));

        ChangedOpenApi result = changedOpenApi.setChangedOperations(changedOperations);

        assertEquals(changedOperations, result.getChangedOperations());
    }
}
