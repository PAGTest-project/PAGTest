package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

class JsonRender_renderTest {

  private JsonRender jsonRender;
  private ObjectMapper mockObjectMapper;
  private OutputStreamWriter mockOutputStreamWriter;

  @BeforeEach
  void setUp() {
    mockObjectMapper = mock(ObjectMapper.class);
    mockOutputStreamWriter = mock(OutputStreamWriter.class);
    jsonRender =
        new JsonRender() {
          @Override
          ObjectMapper initializeObjectMapper() {
            return mockObjectMapper;
          }
        };
  }

  @Test
  void testRenderSuccess() throws IOException {
    ChangedOpenApi diff = new ChangedOpenApi(null);
    StringWriter stringWriter = new StringWriter();
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stringWriter);

    jsonRender.render(diff, outputStreamWriter);

    verify(mockObjectMapper).writeValue(outputStreamWriter, diff);
    outputStreamWriter.close();
  }

  @Test
  void testRenderJsonProcessingException() throws IOException {
    ChangedOpenApi diff = new ChangedOpenApi(null);
    doThrow(JsonProcessingException.class)
        .when(mockObjectMapper)
        .writeValue(any(OutputStreamWriter.class), any(ChangedOpenApi.class));

    assertThrows(RendererException.class, () -> jsonRender.render(diff, mockOutputStreamWriter));
  }

  @Test
  void testRenderIOException() throws IOException {
    ChangedOpenApi diff = new ChangedOpenApi(null);
    doThrow(IOException.class)
        .when(mockObjectMapper)
        .writeValue(any(OutputStreamWriter.class), any(ChangedOpenApi.class));

    assertThrows(RendererException.class, () -> jsonRender.render(diff, mockOutputStreamWriter));
  }
}
