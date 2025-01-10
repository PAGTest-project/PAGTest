
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

        whenNew(FileOutputStream.class).withArguments(Paths.get(fileName).toFile()).thenReturn(fileOutputStream);
        whenNew(OutputStreamWriter.class).withArguments(fileOutputStream).thenReturn(outputStreamWriter);

        // When
        FileUtils.writeToFile(render, diff, fileName);

        // Then
        verify(render).render(diff, outputStreamWriter);
    }

    @Test
    void testWriteToFile_NullFileName() {
        // Given
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = null;

        // When
        FileUtils.writeToFile(render, diff, fileName);

        // Then
        // No exception should be thrown, and no further actions should be taken
    }

    @Test
    void testWriteToFile_EmptyFileName() {
        // Given
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = "";

        // When
        FileUtils.writeToFile(render, diff, fileName);

        // Then
        // No exception should be thrown, and no further actions should be taken
    }

    @Test
    void testWriteToFile_IOException() throws IOException {
        // Given
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = "testFile.txt";

        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        whenNew(FileOutputStream.class).withArguments(Paths.get(fileName).toFile()).thenReturn(fileOutputStream);
        whenNew(OutputStreamWriter.class).withArguments(fileOutputStream).thenReturn(outputStreamWriter);

        doThrow(new IOException("Test Exception")).when(render).render(diff, outputStreamWriter);

        // When
        FileUtils.writeToFile(render, diff, fileName);

        // Then
        // Exception should be logged, and no further actions should be taken
    }
}
