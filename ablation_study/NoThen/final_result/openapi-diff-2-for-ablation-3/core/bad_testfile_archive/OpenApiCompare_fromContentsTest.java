package org.openapitools.openapidiff.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.AuthorizationValue;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

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
    OpenApiCompare openApiCompare = Mockito.spy(new OpenApiCompare());
    try {
      Mockito.doReturn(mockOldSpec).when(openApiCompare).readContent(oldContent, auths);
      Mockito.doReturn(mockNewSpec).when(openApiCompare).readContent(newContent, auths);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // When
    ChangedOpenApi result = openApiCompare.fromContents(oldContent, newContent, auths, options);

    // Then
    assertNotNull(result);
  }
}
