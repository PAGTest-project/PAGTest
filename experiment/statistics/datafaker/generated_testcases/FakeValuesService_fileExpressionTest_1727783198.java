
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import net.datafaker.providers.base.BaseFaker;

public class FakeValuesService_fileExpressionTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        context = new FakerContext(Locale.ENGLISH, new RandomService());
        fakeValuesService = new FakeValuesService(context);
    }

    @Test
    public void testFileExpressionSuccess() throws IOException {
        // Given
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, List.of("Hello", "World"));

        // When
        String result = fakeValuesService.fileExpression(tempFile, new BaseFaker(), context);

        // Then
        assertEquals("Hello" + System.lineSeparator() + "World", result);
    }

    @Test
    public void testFileExpressionIOException() {
        // Given
        Path nonExistentFile = Path.of("nonExistentFile.txt");

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fakeValuesService.fileExpression(nonExistentFile, new BaseFaker(), context);
        });

        assertEquals("Failed to read \"nonExistentFile.txt\"", exception.getMessage());
    }
}
