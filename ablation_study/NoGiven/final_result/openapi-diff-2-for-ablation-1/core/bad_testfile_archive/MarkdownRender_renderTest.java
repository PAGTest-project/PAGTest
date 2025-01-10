package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public class MarkdownRender_renderTest {

  @Test
  public void testRender_Successful() throws IOException {
    // Given
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getChangedOperations()).thenReturn(Collections.emptyList());

    MarkdownRender markdownRender = new MarkdownRender();

    // When
    markdownRender.render(diff, outputStreamWriter);

    // Then
    verify(outputStreamWriter).close();
  }

  @Test
  public void testRender_IOException() throws IOException {
    // Given
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getChangedOperations()).thenReturn(Collections.emptyList());

    doThrow(new IOException("Test Exception")).when(outputStreamWriter).close();

    MarkdownRender markdownRender = new MarkdownRender();

    // When & Then
    assertThrows(RendererException.class, () -> markdownRender.render(diff, outputStreamWriter));
  }
}
