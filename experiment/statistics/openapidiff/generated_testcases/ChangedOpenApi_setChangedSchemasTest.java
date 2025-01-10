
package org.openapitools.openapidiff.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOpenApi_setChangedSchemasTest {

    private ChangedOpenApi changedOpenApi;
    private OpenApiDiffOptions options;

    @BeforeEach
    public void setUp() {
        options = OpenApiDiffOptions.fromConfig(new org.apache.commons.configuration2.CompositeConfiguration());
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testSetChangedSchemas() {
        List<ChangedSchema> changedSchemas = Collections.singletonList(new ChangedSchema());
        ChangedOpenApi result = changedOpenApi.setChangedSchemas(changedSchemas);

        assertEquals(changedSchemas, changedOpenApi.getChangedSchemas());
        assertEquals(changedOpenApi, result);
    }
}
