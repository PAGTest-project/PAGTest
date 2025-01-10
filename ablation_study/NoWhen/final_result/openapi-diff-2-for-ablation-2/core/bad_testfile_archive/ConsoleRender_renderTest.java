package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public class ConsoleRender_renderTest {

  @Mock private ChangedOpenApi diff;

  @Mock private OutputStreamWriter outputStreamWriter;

  private ConsoleRender consoleRender;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    consoleRender = new ConsoleRender();
  }

  @Test
  public void testRenderUnchanged() throws IOException {
    when(diff.isUnchanged()).thenReturn(true);

    consoleRender.render(diff, outputStreamWriter);

    verify(outputStreamWriter).append("No differences. Specifications are equivalents");
    verify(outputStreamWriter).close();
  }

  @Test
  public void testRenderChanged() throws IOException {
    when(diff.isUnchanged()).thenReturn(false);
    when(diff.getNewSpecOpenApi()).thenReturn(mock(ChangedOpenApi.NewSpecOpenApi.class));
    when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(ChangedOpenApi.Info.class));
    when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test API");
    when(diff.getNewEndpoints()).thenReturn(mock(List.class));
    when(diff.getMissingEndpoints()).thenReturn(mock(List.class));
    when(diff.getDeprecatedEndpoints()).thenReturn(mock(List.class));
    when(diff.getChangedOperations()).thenReturn(mock(List.class));
    when(diff.isCompatible()).thenReturn(true);

    consoleRender.render(diff, outputStreamWriter);

    verify(outputStreamWriter).append(startsWith("Api Change Log"));
    verify(outputStreamWriter).append(contains("Test API"));
    verify(outputStreamWriter).append(contains("API changes are backward compatible"));
    verify(outputStreamWriter).close();
  }

  @Test
  public void testRenderIOException() throws IOException {
    when(diff.isUnchanged()).thenReturn(true);
    doThrow(new IOException()).when(outputStreamWriter).close();

    assertThrows(
        RendererException.class,
        () -> {
          consoleRender.render(diff, outputStreamWriter);
        });
  }
}
