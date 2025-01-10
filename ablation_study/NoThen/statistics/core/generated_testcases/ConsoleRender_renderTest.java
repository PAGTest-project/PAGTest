
package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.model.*;
import org.openapitools.openapidiff.core.exception.RendererException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

class ConsoleRender_renderTest {

    @Mock
    private ChangedOpenApi diff;

    @Mock
    private OutputStreamWriter outputStreamWriter;

    private ConsoleRender consoleRender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consoleRender = new ConsoleRender();
    }

    @Test
    void testRenderUnchanged() throws IOException {
        when(diff.isUnchanged()).thenReturn(true);

        consoleRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter).append("No differences. Specifications are equivalents");
        verify(outputStreamWriter).close();
    }

    @Test
    void testRenderChanged() throws IOException {
        when(diff.isUnchanged()).thenReturn(false);
        when(diff.getNewSpecOpenApi()).thenReturn(new OpenAPI());
        when(diff.getNewSpecOpenApi().getInfo()).thenReturn(new Info());
        when(diff.getNewSpecOpenApi().getInfo().getTitle()).thenReturn("Test API");
        when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getChangedOperations()).thenReturn(Collections.emptyList());
        when(diff.isCompatible()).thenReturn(true);

        consoleRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter).append(anyString());
        verify(outputStreamWriter).close();
    }

    @Test
    void testRenderIOException() throws IOException {
        when(diff.isUnchanged()).thenReturn(true);
        doThrow(new IOException("Test Exception")).when(outputStreamWriter).close();

        assertThrows(RendererException.class, () -> {
            consoleRender.render(diff, outputStreamWriter);
        });
    }
}
