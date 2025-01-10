
package org.openapitools.openapidiff.core;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OpenApiCompare_fromSpecificationsTest {

    @Test
    public void testFromSpecifications_Success() {
        OpenAPI oldSpec = new OpenAPI();
        OpenAPI newSpec = new OpenAPI();
        OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

        ChangedOpenApi result = OpenApiCompare.fromSpecifications(oldSpec, newSpec, options);

        assertNotNull(result);
    }

    @Test
    public void testFromSpecifications_NullOldSpec() {
        OpenAPI newSpec = new OpenAPI();
        OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

        assertThrows(RuntimeException.class, () -> {
            OpenApiCompare.fromSpecifications(null, newSpec, options);
        });
    }

    @Test
    public void testFromSpecifications_NullNewSpec() {
        OpenAPI oldSpec = new OpenAPI();
        OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

        assertThrows(RuntimeException.class, () -> {
            OpenApiCompare.fromSpecifications(oldSpec, null, options);
        });
    }
}
