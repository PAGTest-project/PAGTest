
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.apache.commons.configuration2.CompositeConfiguration;

public class ChangedOpenApi_setNewSpecOpenApiTest {

    @Test
    public void testSetNewSpecOpenApi() {
        // Given
        CompositeConfiguration config = new CompositeConfiguration();
        OpenApiDiffOptions options = new OpenApiDiffOptions(config);
        ChangedOpenApi changedOpenApi = new ChangedOpenApi(options);
        OpenAPI newSpecOpenApi = new OpenAPI();

        // When
        ChangedOpenApi result = changedOpenApi.setNewSpecOpenApi(newSpecOpenApi);

        // Then
        assertSame(newSpecOpenApi, result.getNewSpecOpenApi());
        assertSame(changedOpenApi, result);
    }
}
