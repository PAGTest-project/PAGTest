package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;

import java.io.OutputStreamWriter;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.Endpoint;

public class HtmlRender_renderTest {

  @Test
  public void testRender() throws Exception {
    // Given
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    when(diff.getNewEndpoints()).thenReturn(Collections.singletonList(new Endpoint()));
    when(diff.getMissingEndpoints()).thenReturn(Collections.singletonList(new Endpoint()));
    when(diff.getDeprecatedEndpoints()).thenReturn(Collections.singletonList(new Endpoint()));
    when(diff.getChangedOperations())
        .thenReturn(Collections.singletonList(new ChangedOperation("path", null, null, null)));

    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    HtmlRender htmlRender = new HtmlRender();

    // When
    htmlRender.render(diff, outputStreamWriter);

    // Then
    verify(diff, times(1)).getNewEndpoints();
    verify(diff, times(1)).getMissingEndpoints();
    verify(diff, times(1)).getDeprecatedEndpoints();
    verify(diff, times(1)).getChangedOperations();
    verify(outputStreamWriter, times(1)).close();
  }
}
