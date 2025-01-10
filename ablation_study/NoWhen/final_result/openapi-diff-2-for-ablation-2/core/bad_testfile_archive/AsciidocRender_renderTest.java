package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public class AsciidocRender_renderTest {

  @Test
  public void testRenderUnchanged() throws IOException {
    // Given
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    when(diff.isUnchanged()).thenReturn(true);
    when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenAPI.class));
    when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
    when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
    when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    AsciidocRender asciidocRender = new AsciidocRender();

    // When
    asciidocRender.render(diff, outputStreamWriter);

    // Then
    verify(outputStreamWriter, times(4)).append(anyString());
    verify(outputStreamWriter).close();
  }

  @Test
  public void testRenderChanged() throws IOException {
    // Given
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    when(diff.isUnchanged()).thenReturn(false);
    when(diff.isCompatible()).thenReturn(true);
    when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenAPI.class));
    when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
    when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
    when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");
    when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getChangedOperations()).thenReturn(Collections.emptyList());

    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    AsciidocRender asciidocRender = new AsciidocRender();

    // When
    asciidocRender.render(diff, outputStreamWriter);

    // Then
    verify(outputStreamWriter, times(6)).append(anyString());
    verify(outputStreamWriter).close();
  }

  @Test
  public void testRenderIOException() throws IOException {
    // Given
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    when(diff.isUnchanged()).thenReturn(true);
    when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenAPI.class));
    when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
    when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
    when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);
    doThrow(new IOException()).when(outputStreamWriter).close();

    AsciidocRender asciidocRender = new AsciidocRender();

    // When
    try {
      asciidocRender.render(diff, outputStreamWriter);
    } catch (RendererException e) {
      // Then
      verify(outputStreamWriter, times(4)).append(anyString());
      verify(outputStreamWriter).close();
      return;
    }
    fail("Expected RendererException to be thrown");
  }
}
