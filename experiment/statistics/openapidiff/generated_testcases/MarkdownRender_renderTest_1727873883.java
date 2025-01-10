
package org.openapitools.openapidiff.core.output;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public class MarkdownRender_renderTest {

    @Mock
    private ChangedOpenApi diff;

    @Mock
    private OutputStreamWriter outputStreamWriter;

    private MarkdownRender markdownRender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        markdownRender = new MarkdownRender();
    }

    @Test
    public void testRender_Successful() throws IOException {
        markdownRender.render(diff, outputStreamWriter);
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRender_IOException() throws IOException {
        doThrow(new IOException()).when(outputStreamWriter).close();
        assertThrows(RendererException.class, () -> markdownRender.render(diff, outputStreamWriter));
    }
}
