package org.openapitools.openapidiff.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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
    OpenApiCompare spyCompare = Mockito.spy(new OpenApiCompare());
    doReturn(mockOldSpec).when(spyCompare).readContent(oldContent, auths);
    doReturn(mockNewSpec).when(spyCompare).readContent(newContent, auths);

    // When
    ChangedOpenApi result = spyCompare.fromContents(oldContent, newContent, auths, options);

    // Then
    assertNotNull(result);
    verify(spyCompare).readContent(oldContent, auths);
    verify(spyCompare).readContent(newContent, auths);
  }
}
