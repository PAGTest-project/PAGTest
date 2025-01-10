
package org.openapitools.openapidiff.core.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import java.io.OutputStreamWriter;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class HtmlRender_renderTest {

    @Test
    public void testRender() throws Exception {
        // Given
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        when(diff.getNewEndpoints()).thenReturn(Collections.singletonList(new Endpoint()));
        when(diff.getMissingEndpoints()).thenReturn(Collections.singletonList(new Endpoint()));
        when(diff.getDeprecatedEndpoints()).thenReturn(Collections.singletonList(new Endpoint()));
        when(diff.getChangedOperations()).thenReturn(Collections.singletonList(new ChangedOperation("path", null, null, null)));

        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        HtmlRender htmlRender = new HtmlRender();

        // When
        htmlRender.render(diff, outputStreamWriter);

        // Then
        verify(diff).getNewEndpoints();
        verify(diff).getMissingEndpoints();
        verify(diff).getDeprecatedEndpoints();
        verify(diff).getChangedOperations();
        verify(outputStreamWriter).close();
    }
}
