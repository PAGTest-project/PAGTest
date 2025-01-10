
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedOpenApi_setOldSpecOpenApiTest {

    @Test
    void testSetOldSpecOpenApi() {
        // Given
        OpenAPI oldSpec = new OpenAPI();
        ChangedOpenApi changedOpenApi = new ChangedOpenApi(null);

        // When
        ChangedOpenApi result = changedOpenApi.setOldSpecOpenApi(oldSpec);

        // Then
        assertSame(oldSpec, result.getOldSpecOpenApi());
        assertSame(changedOpenApi, result);
    }
}
