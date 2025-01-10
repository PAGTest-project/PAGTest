
package org.openapitools.openapidiff.core.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.Endpoint;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class AsciidocRender_renderTest {

    @Test
    public void testRenderUnchanged() throws IOException {
        // Given
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        when(diff.isUnchanged()).thenReturn(true);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenApi.class));
        when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
        when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
        when(diff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        AsciidocRender asciidocRender = new AsciidocRender();

        // When
        asciidocRender.render(diff, outputStreamWriter);

        // Then
        verify(outputStreamWriter, times(4)).write(anyString());
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderChanged() throws IOException {
        // Given
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        when(diff.isUnchanged()).thenReturn(false);
        when(diff.isCompatible()).thenReturn(true);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenApi.class));
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
        verify(outputStreamWriter, times(6)).write(anyString());
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderIOException() throws IOException {
        // Given
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        when(diff.isUnchanged()).thenReturn(true);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(OpenApi.class));
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
            verify(outputStreamWriter, times(4)).write(anyString());
            verify(outputStreamWriter).close();
            return;
        }
        fail("Expected RendererException to be thrown");
    }
}
