
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakeValuesService_fileExpressionTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;
    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        context = new FakerContext(Locale.ENGLISH, new RandomService());
        fakeValuesService = new FakeValuesService(context);
        faker = new BaseFaker(fakeValuesService, context);
    }

    @Test
    void testFileExpressionSuccess() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, List.of("line1", "line2", "line3"));

        String result = fakeValuesService.fileExpression(tempFile, faker, context);
        String expected = "line1" + System.lineSeparator() + "line2" + System.lineSeparator() + "line3";

        assertEquals(expected, result);
    }

    @Test
    void testFileExpressionFileNotFound() {
        Path nonExistentFile = Path.of("nonExistentFile.txt");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fakeValuesService.fileExpression(nonExistentFile, faker, context);
        });

        assertEquals("Failed to read \"nonExistentFile.txt\"", exception.getMessage());
    }

    @Test
    void testFileExpressionEmptyFile() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");

        String result = fakeValuesService.fileExpression(tempFile, faker, context);

        assertEquals("", result);
    }
}
