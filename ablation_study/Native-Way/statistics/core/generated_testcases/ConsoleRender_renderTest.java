
package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.*;
import org.openapitools.openapidiff.core.exception.RendererException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

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
        OpenAPI newSpecOpenApi = new OpenAPI();
        Info info = new Info();
        info.setTitle("Test API");
        newSpecOpenApi.setInfo(info);
        when(diff.getNewSpecOpenApi()).thenReturn(newSpecOpenApi);
        when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getChangedOperations()).thenReturn(Collections.emptyList());
        when(diff.isCompatible()).thenReturn(true);

        consoleRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter, times(1)).append(Mockito.anyString());
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderIOException() throws IOException {
        when(diff.isUnchanged()).thenReturn(true);
        doThrow(new IOException("Test Exception")).when(outputStreamWriter).close();

        try {
            consoleRender.render(diff, outputStreamWriter);
        } catch (RendererException e) {
            assert(e.getCause().getMessage().equals("Test Exception"));
        }
    }
}
