
package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.model.ChangedOperation;

@ExtendWith(MockitoExtension.class)
public class MarkdownRender_renderTest {

    @Mock
    private ChangedOpenApi diff;

    @Mock
    private OutputStreamWriter outputStreamWriter;

    private MarkdownRender markdownRender;

    @BeforeEach
    public void setUp() {
        markdownRender = new MarkdownRender();
    }

    @Test
    public void testRender_Successful() throws IOException {
        when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getChangedOperations()).thenReturn(Collections.emptyList());

        markdownRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter).close();
    }

    @Test
    public void testRender_IOException() throws IOException {
        when(diff.getNewEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getMissingEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getDeprecatedEndpoints()).thenReturn(Collections.emptyList());
        when(diff.getChangedOperations()).thenReturn(Collections.emptyList());
        doThrow(IOException.class).when(outputStreamWriter).close();

        assertThrows(RendererException.class, () -> {
            markdownRender.render(diff, outputStreamWriter);
        });
    }
}
