package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.info.Info;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.Endpoint;

public class ConsoleRender_renderTest {

  private ConsoleRender consoleRender;
  private ChangedOpenApi diff;
  private OutputStreamWriter outputStreamWriter;

  @BeforeEach
  public void setUp() {
    consoleRender = new ConsoleRender();
    diff = mock(ChangedOpenApi.class);
    outputStreamWriter = mock(OutputStreamWriter.class);
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
    ChangedOpenApi newSpecOpenApi = mock(ChangedOpenApi.class);
    when(diff.getNewSpecOpenApi()).thenReturn(newSpecOpenApi);
    Info info = mock(Info.class);
    when(newSpecOpenApi.getInfo()).thenReturn(info);
    when(info.getTitle()).thenReturn("Test API");
    when(diff.getNewEndpoints()).thenReturn(List.of(mock(Endpoint.class)));
    when(diff.getMissingEndpoints()).thenReturn(List.of(mock(Endpoint.class)));
    when(diff.getDeprecatedEndpoints()).thenReturn(List.of(mock(Endpoint.class)));
    when(diff.getChangedOperations()).thenReturn(List.of(mock(ChangedOperation.class)));
    when(diff.isCompatible()).thenReturn(true);

    consoleRender.render(diff, outputStreamWriter);

    verify(outputStreamWriter, atLeastOnce()).append(Mockito.anyString());
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
