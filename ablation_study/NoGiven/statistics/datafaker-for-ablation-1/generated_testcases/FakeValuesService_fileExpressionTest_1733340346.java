
package net.datafaker.service;

import net.datafaker.providers.base.BaseFaker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FakeValuesService_fileExpressionTest {

    @Test
    public void testFileExpression_Success() throws IOException {
        // Given
        Path path = mock(Path.class);
        BaseFaker faker = mock(BaseFaker.class);
        FakerContext context = mock(FakerContext.class);
        List<String> lines = List.of("line1", "line2");
        when(Files.readAllLines(path)).thenReturn(lines);
        when(faker.expression(anyString(), eq(faker), eq(context))).thenReturn("processedLine1", "processedLine2");

        // When
        String result = new FakeValuesService().fileExpression(path, faker, context);

        // Then
        assertEquals("processedLine1" + System.lineSeparator() + "processedLine2", result);
    }

    @Test
    public void testFileExpression_IOException() throws IOException {
        // Given
        Path path = mock(Path.class);
        BaseFaker faker = mock(BaseFaker.class);
        FakerContext context = mock(FakerContext.class);
        when(Files.readAllLines(path)).thenThrow(new IOException("Test exception"));

        // When and Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new FakeValuesService().fileExpression(path, faker, context);
        });
        assertEquals("Failed to read \"%s\"".formatted(path), exception.getMessage());
    }
}
