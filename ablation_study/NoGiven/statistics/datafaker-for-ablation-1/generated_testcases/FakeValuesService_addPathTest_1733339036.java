
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
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
    void testAddPathValidFile() {
        Locale locale = Locale.ENGLISH;
        Path path = Paths.get("src/test/resources/validfile.txt");
        assertDoesNotThrow(() -> fakeValuesService.addPath(locale, path));
    }

    @Test
    void testAddPathNullPath() {
        Locale locale = Locale.ENGLISH;
        Path path = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertEquals("Path should be an existing readable file: \"null\"", exception.getMessage());
    }

    @Test
    void testAddPathNonExistentFile() {
        Locale locale = Locale.ENGLISH;
        Path path = Paths.get("src/test/resources/nonexistentfile.txt");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertTrue(exception.getMessage().startsWith("Path should be an existing readable file:"));
    }

    @Test
    void testAddPathDirectory() {
        Locale locale = Locale.ENGLISH;
        Path path = Paths.get("src/test/resources");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertTrue(exception.getMessage().startsWith("Path should be an existing readable file:"));
    }

    @Test
    void testAddPathUnreadableFile() {
        Locale locale = Locale.ENGLISH;
        Path path = Paths.get("src/test/resources/unreadablefile.txt");
        try {
            Files.setAttribute(path, "dos:readonly", true);
        } catch (Exception e) {
            fail("Failed to set file as unreadable");
        }
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertTrue(exception.getMessage().startsWith("Path should be an existing readable file:"));
    }

    @Test
    void testAddPathMalformedURL() {
        Locale locale = Locale.ENGLISH;
        Path path = Paths.get("src/test/resources/invalidfile.txt");
        try {
            Files.write(path, "invalid url".getBytes());
        } catch (Exception e) {
            fail("Failed to write to file");
        }
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertTrue(exception.getMessage().startsWith("Failed to read"));
    }
}
