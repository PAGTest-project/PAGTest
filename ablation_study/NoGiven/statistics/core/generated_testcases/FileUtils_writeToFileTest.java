
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
    void testWriteToFile_Success() throws IOException {
        // Given
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = "testFile.txt";

        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        when(fileOutputStream.getChannel()).thenReturn(null); // Mocking the channel to avoid actual file creation
        when(fileOutputStream.getFD()).thenReturn(null); // Mocking the file descriptor to avoid actual file creation

        // When
        FileUtils.writeToFile(render, diff, fileName);

        // Then
        verify(render).render(eq(diff), any(OutputStreamWriter.class));
    }

    @Test
    void testWriteToFile_IOException() throws IOException {
        // Given
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = "testFile.txt";

        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        when(fileOutputStream.getChannel()).thenReturn(null); // Mocking the channel to avoid actual file creation
        when(fileOutputStream.getFD()).thenReturn(null); // Mocking the file descriptor to avoid actual file creation

        doThrow(new IOException("Test Exception")).when(fileOutputStream).close();

        // When
        FileUtils.writeToFile(render, diff, fileName);

        // Then
        verify(render, never()).render(any(), any());
    }

    @Test
    void testWriteToFile_NullOrEmptyFileName() {
        // Given
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = "";

        // When
        FileUtils.writeToFile(render, diff, fileName);

        // Then
        verify(render, never()).render(any(), any());
    }
}
