package org.openapitools.openapidiff.core.utils;

import static org.mockito.Mockito.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.output.Render;

class FileUtils_writeToFileTest {

  @Test
  void testWriteToFile_Success() throws IOException {
    Render render = mock(Render.class);
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    String fileName = "testFile.txt";

    FileOutputStream fileOutputStream = mock(FileOutputStream.class);
    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    whenNew(FileOutputStream.class)
        .withArguments(any(java.io.File.class))
        .thenReturn(fileOutputStream);
    whenNew(OutputStreamWriter.class)
        .withArguments(any(FileOutputStream.class))
        .thenReturn(outputStreamWriter);

    FileUtils.writeToFile(render, diff, fileName);

    verify(render).render(diff, outputStreamWriter);
  }

  @Test
  void testWriteToFile_FileNameNullOrEmpty() {
    Render render = mock(Render.class);
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    String fileName = null;

    FileUtils.writeToFile(render, diff, fileName);

    fileName = "";
    FileUtils.writeToFile(render, diff, fileName);
  }

  @Test
  void testWriteToFile_IOException() throws IOException {
    Render render = mock(Render.class);
    ChangedOpenApi diff = mock(ChangedOpenApi.class);
    String fileName = "testFile.txt";

    FileOutputStream fileOutputStream = mock(FileOutputStream.class);
    OutputStreamWriter outputStreamWriter = mock(OutputStreamWriter.class);

    whenNew(FileOutputStream.class)
        .withArguments(any(java.io.File.class))
        .thenReturn(fileOutputStream);
    whenNew(OutputStreamWriter.class)
        .withArguments(any(FileOutputStream.class))
        .thenReturn(outputStreamWriter);

    doThrow(new IOException("Test Exception")).when(outputStreamWriter).close();

    FileUtils.writeToFile(render, diff, fileName);

    verify(render, never()).render(any(), any());
  }
}
