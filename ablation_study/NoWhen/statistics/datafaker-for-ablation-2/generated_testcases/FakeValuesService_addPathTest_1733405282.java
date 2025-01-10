
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_addPathTest {
    private FakeValuesService fakeValuesService;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
    }

    @Test
    void testAddPathValidFile() throws MalformedURLException {
        Locale locale = Locale.US;
        Path path = Paths.get("src/test/resources/validfile.txt");
        assertDoesNotThrow(() -> fakeValuesService.addPath(locale, path));
    }

    @Test
    void testAddPathNullPath() {
        Locale locale = Locale.US;
        Path path = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertEquals("Path should be an existing readable file: \"null\"", exception.getMessage());
    }

    @Test
    void testAddPathNonExistentFile() {
        Locale locale = Locale.US;
        Path path = Paths.get("src/test/resources/nonexistentfile.txt");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertEquals("Path should be an existing readable file: \"%s\"".formatted(path), exception.getMessage());
    }

    @Test
    void testAddPathDirectory() {
        Locale locale = Locale.US;
        Path path = Paths.get("src/test/resources");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertEquals("Path should be an existing readable file: \"%s\"".formatted(path), exception.getMessage());
    }

    @Test
    void testAddPathUnreadableFile() {
        Locale locale = Locale.US;
        Path path = Paths.get("src/test/resources/unreadablefile.txt");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertEquals("Path should be an existing readable file: \"%s\"".formatted(path), exception.getMessage());
    }
}
