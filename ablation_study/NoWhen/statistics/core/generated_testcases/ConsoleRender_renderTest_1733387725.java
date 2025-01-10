
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

public class ConsoleRender_renderTest {

    @Mock
    private ChangedOpenApi diff;

    @Mock
    private OutputStreamWriter outputStreamWriter;

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

        verify(outputStreamWriter).write("No differences. Specifications are equivalents");
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderChanged() throws IOException {
        when(diff.isUnchanged()).thenReturn(false);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(ChangedOpenApi.NewSpecOpenApi.class));
        when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(ChangedOpenApi.Info.class));
        when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test API");
        when(diff.getNewEndpoints()).thenReturn(mock(java.util.List.class));
        when(diff.getMissingEndpoints()).thenReturn(mock(java.util.List.class));
        when(diff.getDeprecatedEndpoints()).thenReturn(mock(java.util.List.class));
        when(diff.getChangedOperations()).thenReturn(mock(java.util.List.class));
        when(diff.isCompatible()).thenReturn(true);

        consoleRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter).write(startsWith("Api Change Log"));
        verify(outputStreamWriter).write(contains("Test API"));
        verify(outputStreamWriter).write(contains("API changes are backward compatible"));
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderIOException() throws IOException {
        when(diff.isUnchanged()).thenReturn(true);
        doThrow(new IOException()).when(outputStreamWriter).close();

        assertThrows(RendererException.class, () -> {
            consoleRender.render(diff, outputStreamWriter);
        });
    }
}
