
package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import java.io.OutputStreamWriter;
import java.io.IOException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.model.ChangedOperation;

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

        verify(outputStreamWriter).write("No differences. Specifications are equivalents");
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderChanged() throws IOException {
        when(diff.isUnchanged()).thenReturn(false);
        when(diff.getNewSpecOpenApi()).thenReturn(mock(ChangedOpenApi.class));
        when(diff.getNewSpecOpenApi().getInfo()).thenReturn(mock(ChangedOpenApi.Info.class));
        when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test API");
        when(diff.getNewEndpoints()).thenReturn(mock(Endpoint.class));
        when(diff.getMissingEndpoints()).thenReturn(mock(Endpoint.class));
        when(diff.getDeprecatedEndpoints()).thenReturn(mock(Endpoint.class));
        when(diff.getChangedOperations()).thenReturn(mock(ChangedOperation.class));
        when(diff.isCompatible()).thenReturn(true);

        consoleRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter).write(Mockito.anyString());
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
