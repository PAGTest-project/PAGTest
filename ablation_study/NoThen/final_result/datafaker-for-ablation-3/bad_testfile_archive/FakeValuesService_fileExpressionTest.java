
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import net.datafaker.providers.base.BaseFaker;

public class FakeValuesService_fileExpressionTest {

    private FakeValuesService fakeValuesService;
    private BaseFaker baseFaker;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        baseFaker = new BaseFaker();
        fakeValuesService = baseFaker.fakeValuesService();
        context = baseFaker.getContext();
    }

    @Test
    public void testFileExpressionSuccess() {
        Path path = Paths.get("src/test/resources/testfile.txt");
        BaseFaker faker = new BaseFaker();
        String result = fakeValuesService.fileExpression(path, faker, context);
        assertEquals("Processed content of testfile.txt", result);
    }

    @Test
    public void testFileExpressionIOException() {
        Path path = Paths.get("nonexistentfile.txt");
        BaseFaker faker = new BaseFaker();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fakeValuesService.fileExpression(path, faker, context);
        });
        assertEquals("Failed to read \"nonexistentfile.txt\"", exception.getMessage());
    }

    @Test
    public void testFileExpressionWithLocale() {
        Path path = Paths.get("src/test/resources/testfile_en_US.txt");
        BaseFaker faker = new BaseFaker(Locale.US);
        String result = fakeValuesService.fileExpression(path, faker, context);
        assertEquals("Processed content of testfile_en_US.txt", result);
    }
}
