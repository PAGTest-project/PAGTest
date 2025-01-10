package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public class AsciidocRender_renderTest {

  @Mock private ChangedOpenApi diff;

  @Mock private OutputStreamWriter outputStreamWriter;

  private AsciidocRender asciidocRender;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    asciidocRender = new AsciidocRender();
  }

  @Test
  public void testRenderUnchanged() throws IOException {
    when(diff.isUnchanged()).thenReturn(true);
    when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
    when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

    asciidocRender.render(diff, outputStreamWriter);

    verify(outputStreamWriter, times(4)).append(anyString());
    verify(outputStreamWriter).close();
  }

  @Test
  public void testRenderChanged() throws IOException {
    when(diff.isUnchanged()).thenReturn(false);
    when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
    when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");
    when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
    when(diff.getChangedOperations()).thenReturn(Collections.emptyList());
    when(diff.isCompatible()).thenReturn(true);

    asciidocRender.render(diff, outputStreamWriter);

    verify(outputStreamWriter, times(6)).append(anyString());
    verify(outputStreamWriter).close();
  }

  @Test
  public void testRenderIOException() throws IOException {
    when(diff.isUnchanged()).thenReturn(true);
    when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
    when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");
    doThrow(new IOException()).when(outputStreamWriter).close();

    assertThrows(
        RendererException.class,
        () -> {
          asciidocRender.render(diff, outputStreamWriter);
        });
  }
}
