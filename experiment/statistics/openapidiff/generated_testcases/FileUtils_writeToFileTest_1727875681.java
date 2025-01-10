
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

        try (FileOutputStream fos = new FileOutputStream(Paths.get(fileName).toFile());
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {
            when(render.render(diff, osw)).thenReturn(null);

            // When
            FileUtils.writeToFile(render, diff, fileName);

            // Then
            verify(render).render(diff, osw);
        }
    }

    @Test
    void testWriteToFile_FileNameNullOrEmpty() {
        // Given
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = null;

        // When
        FileUtils.writeToFile(render, diff, fileName);

        // Then
        // No exception should be thrown, and the method should return early
    }

    @Test
    void testWriteToFile_IOException() throws IOException {
        // Given
        Render render = mock(Render.class);
        ChangedOpenApi diff = mock(ChangedOpenApi.class);
        String fileName = "testFile.txt";

        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

        try (FileOutputStream fos = new FileOutputStream(Paths.get(fileName).toFile());
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {
            doThrow(new IOException("Test Exception")).when(fos).close();

            // When
            FileUtils.writeToFile(render, diff, fileName);

            // Then
            // Exception should be logged and handled
        }
    }
}
