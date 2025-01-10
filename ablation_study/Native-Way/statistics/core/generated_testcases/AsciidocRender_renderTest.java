
package org.openapitools.openapidiff.core.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.exception.RendererException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class AsciidocRender_renderTest {

    @Test
    public void testRenderUnchanged() throws Exception {
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        when(diff.isUnchanged()).thenReturn(true);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenAPI.class));
        when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
        when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
        when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

        AsciidocRender asciidocRender = new AsciidocRender();
        asciidocRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter, times(4)).append(anyString());
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderChanged() throws Exception {
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

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

        AsciidocRender asciidocRender = new AsciidocRender();
        asciidocRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter, times(6)).append(anyString());
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderIOException() throws Exception {
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        when(diff.isUnchanged()).thenReturn(true);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenAPI.class));
        when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
        when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
        when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

        doThrow(new IOException()).when(outputStreamWriter).close();

        AsciidocRender asciidocRender = new AsciidocRender();
        try {
            asciidocRender.render(diff, outputStreamWriter);
        } catch (RendererException e) {
            // Expected exception
        }

        verify(outputStreamWriter, times(4)).append(anyString());
        verify(outputStreamWriter).close();
    }
}
