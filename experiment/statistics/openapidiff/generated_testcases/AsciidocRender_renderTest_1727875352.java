
package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.OutputStreamWriter;
import java.io.IOException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.exception.RendererException;
import java.util.Collections;

public class AsciidocRender_renderTest {

    @Mock
    private ChangedOpenApi mockDiff;

    @Mock
    private OutputStreamWriter mockOutputStreamWriter;

    private AsciidocRender asciidocRender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        asciidocRender = new AsciidocRender();
    }

    @Test
    public void testRenderUnchanged() throws IOException {
        when(mockDiff.isUnchanged()).thenReturn(true);
        when(mockDiff.getNewSpecOpenApi()).thenReturn(mock(OpenApi.class));
        when(mockDiff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
        when(mockDiff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
        when(mockDiff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");

        asciidocRender.render(mockDiff, mockOutputStreamWriter);

        verify(mockOutputStreamWriter, times(4)).write(anyString());
        verify(mockOutputStreamWriter).close();
    }

    @Test
    public void testRenderChanged() throws IOException {
        when(mockDiff.isUnchanged()).thenReturn(false);
        when(mockDiff.isCompatible()).thenReturn(true);
        when(mockDiff.getNewSpecOpenApi()).thenReturn(mock(OpenApi.class));
        when(mockDiff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
        when(mockDiff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
        when(mockDiff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");
        when(mockDiff.getNewEndpoints()).thenReturn(Collections.emptyList());
        when(mockDiff.getMissingEndpoints()).thenReturn(Collections.emptyList());
        when(mockDiff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
        when(mockDiff.getChangedOperations()).thenReturn(Collections.emptyList());

        asciidocRender.render(mockDiff, mockOutputStreamWriter);

        verify(mockOutputStreamWriter, times(6)).write(anyString());
        verify(mockOutputStreamWriter).close();
    }

    @Test
    public void testRenderIOException() throws IOException {
        when(mockDiff.isUnchanged()).thenReturn(true);
        when(mockDiff.getNewSpecOpenApi()).thenReturn(mock(OpenApi.class));
        when(mockDiff.getNewSpecOpenApi().getInfo()).thenReturn(mock(Info.class));
        when(mockDiff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test Title");
        when(mockDiff.getNewSpecOpenApi().getInfo().getVersion()).thenReturn("1.0");
        doThrow(new IOException()).when(mockOutputStreamWriter).close();

        assertThrows(RendererException.class, () -> {
            asciidocRender.render(mockDiff, mockOutputStreamWriter);
        });
    }
}
