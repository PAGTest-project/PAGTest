
package org.openapitools.openapidiff.core.utils;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.output.Render;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;

class FileUtils_writeToFileTest {

    @Test
    void testWriteToFile_fileNameIsNull() {
        FileUtils.writeToFile(mock(Render.class), mock(ChangedOpenApi.class), null);
        // No exception should be thrown, and the method should return early
    }

    @Test
    void testWriteToFile_fileNameIsEmpty() {
        FileUtils.writeToFile(mock(Render.class), mock(ChangedOpenApi.class), "");
        // No exception should be thrown, and the method should return early
    }

    @Test
    void testWriteToFile_successfulWrite() throws IOException {
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = "testFile.txt";

        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        when(fileOutputStream.getChannel()).thenReturn(null); // Mocking to avoid actual file creation
        whenNew(FileOutputStream.class).withArguments(Paths.get(fileName).toFile()).thenReturn(fileOutputStream);
        whenNew(OutputStreamWriter.class).withArguments(fileOutputStream).thenReturn(outputStreamWriter);

        FileUtils.writeToFile(render, diff, fileName);

        verify(render).render(diff, outputStreamWriter);
    }

    @Test
    void testWriteToFile_IOException() throws IOException {
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = "testFile.txt";

        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        when(fileOutputStream.getChannel()).thenReturn(null); // Mocking to avoid actual file creation
        whenNew(FileOutputStream.class).withArguments(Paths.get(fileName).toFile()).thenReturn(fileOutputStream);
        whenNew(OutputStreamWriter.class).withArguments(fileOutputStream).thenReturn(outputStreamWriter);

        doThrow(new IOException("Mocked IOException")).when(outputStreamWriter).close();

        FileUtils.writeToFile(render, diff, fileName);

        // Verify that the logger error method was called
        verify(logger).error(anyString(), eq(fileName), any(IOException.class));
    }
}
