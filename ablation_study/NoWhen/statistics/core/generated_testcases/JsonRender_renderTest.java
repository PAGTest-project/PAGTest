
package org.openapitools.openapidiff.core.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class JsonRender_renderTest {

    @Test
    public void testRenderSuccess() throws IOException {
        // Given
        JsonRender jsonRender = new JsonRender();
        ChangedOpenApi diff = Mockito.mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = Mockito.mock(OutputStreamWriter.class);

        // When
        jsonRender.render(diff, outputStreamWriter);

        // Then
        verify(outputStreamWriter).close();
    }

    @Test
    public void testRenderJsonProcessingException() throws IOException {
        // Given
        JsonRender jsonRender = new JsonRender();
        ChangedOpenApi diff = Mockito.mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = Mockito.mock(OutputStreamWriter.class);
        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);
        jsonRender.objectMapper = mockObjectMapper;
        doThrow(JsonProcessingException.class).when(mockObjectMapper).writeValue(outputStreamWriter, diff);

        // When & Then
        assertThrows(RendererException.class, () -> jsonRender.render(diff, outputStreamWriter));
    }

    @Test
    public void testRenderIOException() throws IOException {
        // Given
        JsonRender jsonRender = new JsonRender();
        ChangedOpenApi diff = Mockito.mock(ChangedOpenApi.class);
        OutputStreamWriter outputStreamWriter = Mockito.mock(OutputStreamWriter.class);
        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);
        jsonRender.objectMapper = mockObjectMapper;
        doThrow(IOException.class).when(mockObjectMapper).writeValue(outputStreamWriter, diff);

        // When & Then
        assertThrows(RendererException.class, () -> jsonRender.render(diff, outputStreamWriter));
    }
}
