
package org.openapitools.openapidiff.core;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.AuthorizationValue;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class OpenApiCompare_fromContentsTest {

    @Test
    public void testFromContents() {
        // Given
        String oldContent = "oldContent";
        String newContent = "newContent";
        List<AuthorizationValue> auths = Collections.emptyList();
        OpenApiDiffOptions options = OpenApiDiffOptions.builder().build();

        OpenAPI mockOldSpec = Mockito.mock(OpenAPI.class);
        OpenAPI mockNewSpec = Mockito.mock(OpenAPI.class);

        // Mocking readContent method
        when(OpenApiCompare.readContent(oldContent, auths)).thenReturn(mockOldSpec);
        when(OpenApiCompare.readContent(newContent, auths)).thenReturn(mockNewSpec);

        // When
        ChangedOpenApi result = OpenApiCompare.fromContents(oldContent, newContent, auths, options);

        // Then
        assertNotNull(result);
    }
}
