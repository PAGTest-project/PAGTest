package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.Endpoint;

public class AsciidocRender_renderTest {

  @Test
  public void testRenderUnchanged() throws Exception {
    // Given
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    when(diff.isUnchanged()).thenReturn(true);
    OpenAPI openAPI = mock(OpenAPI.class);
    Info info = mock(Info.class);
    when(openAPI.getInfo()).thenReturn(info);
    when(info.getTitle()).thenReturn("Test Title");
    when(info.getVersion()).thenReturn("1.0");
    when(diff.getNewSpecOpenApi()).thenReturn(openAPI);

    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    AsciidocRender asciidocRender = new AsciidocRender();

    // When
    asciidocRender.render(diff, outputStreamWriter);

    // Then
    verify(outputStreamWriter, times(4)).append(anyString());
    verify(outputStreamWriter).close();
  }

  @Test
  public void testRenderChanged() throws Exception {
    // Given
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    when(diff.isUnchanged()).thenReturn(false);
    when(diff.isCompatible()).thenReturn(true);
    OpenAPI openAPI = mock(OpenAPI.class);
    Info info = mock(Info.class);
    when(openAPI.getInfo()).thenReturn(info);
    when(info.getTitle()).thenReturn("Test Title");
    when(info.getVersion()).thenReturn("1.0");
    when(diff.getNewSpecOpenApi()).thenReturn(openAPI);

    List<Endpoint> newEndpoints = Collections.emptyList();
    List<Endpoint> missingEndpoints = Collections.emptyList();
    List<Endpoint> deprecatedEndpoints = Collections.emptyList();
    List<ChangedOperation> changedOperations = Collections.emptyList();

    when(diff.getNewEndpoints()).thenReturn(newEndpoints);
    when(diff.getMissingEndpoints()).thenReturn(missingEndpoints);
    when(diff.getDeprecatedEndpoints()).thenReturn(deprecatedEndpoints);
    when(diff.getChangedOperations()).thenReturn(changedOperations);

    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    AsciidocRender asciidocRender = new AsciidocRender();

    // When
    asciidocRender.render(diff, outputStreamWriter);

    // Then
    verify(outputStreamWriter, times(6)).append(anyString());
    verify(outputStreamWriter).close();
  }
}
