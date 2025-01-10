
package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;

class MarkdownRender_renderTest {

    @Mock
    private ChangedOpenApi diff;

    @Mock
    private OutputStreamWriter outputStreamWriter;

    private MarkdownRender markdownRender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        markdownRender = new MarkdownRender();
    }

    @Test
    void testRender_Successful() throws IOException {
        when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getChangedOperations()).thenReturn(Collections.emptyList());

        markdownRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter).close();
    }

    @Test
    void testRender_IOException() throws IOException {
        when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getChangedOperations()).thenReturn(Collections.emptyList());
        doThrow(new IOException()).when(outputStreamWriter).close();

        assertThrows(RendererException.class, () -> {
            markdownRender.render(diff, outputStreamWriter);
        });
    }
}
