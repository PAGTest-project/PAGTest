
package org.openapitools.openapidiff.core.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class JsonRender_renderTest {

    @Test
    public void testRenderSuccess() throws IOException {
        JsonRender jsonRender = new JsonRender();
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        jsonRender.render(diff, outputStreamWriter);

        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderJsonProcessingException() throws IOException {
        JsonRender jsonRender = new JsonRender();
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);
        doThrow(JsonProcessingException.class).when(jsonRender.objectMapper).writeValue(outputStreamWriter, diff);

        assertThrows(RendererException.class, () -> jsonRender.render(diff, outputStreamWriter));
    }

    @Test
    public void testRenderIOException() throws IOException {
        JsonRender jsonRender = new JsonRender();
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);
        doThrow(IOException.class).when(jsonRender.objectMapper).writeValue(outputStreamWriter, diff);

        assertThrows(RendererException.class, () -> jsonRender.render(diff, outputStreamWriter));
    }
}
