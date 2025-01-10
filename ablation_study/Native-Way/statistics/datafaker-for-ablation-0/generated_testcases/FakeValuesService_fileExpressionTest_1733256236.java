
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import net.datafaker.providers.base.BaseFaker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakeValuesService_fileExpressionTest {

    private FakeValuesService fakeValuesService;
    private BaseFaker baseFaker;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        baseFaker = new BaseFaker();
        context = new FakerContext(Locale.ENGLISH, baseFaker.random());
    }

    @Test
    public void testFileExpressionSuccess() {
        Path path = Paths.get("src/test/resources/testfile.txt");
        String expected = "Test expression";
        String result = fakeValuesService.fileExpression(path, baseFaker, context);
        assertEquals(expected, result);
    }

    @Test
    public void testFileExpressionIOException() {
        Path path = Paths.get("nonexistentfile.txt");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fakeValuesService.fileExpression(path, baseFaker, context);
        });
        assertEquals("Failed to read \"nonexistentfile.txt\"", exception.getMessage());
    }
}
