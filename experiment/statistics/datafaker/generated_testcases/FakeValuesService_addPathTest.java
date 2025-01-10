
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testAddPathSuccess() {
        Locale locale = Locale.US;
        Path path = Paths.get("src/test/resources/testfile.txt");
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
        Path path = Paths.get("non_existent_file.txt");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertEquals("Path should be an existing readable file: \"non_existent_file.txt\"", exception.getMessage());
    }

    @Test
    void testAddPathDirectory() {
        Locale locale = Locale.US;
        Path path = Paths.get("src/test/resources");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertEquals("Path should be an existing readable file: \"src/test/resources\"", exception.getMessage());
    }

    @Test
    void testAddPathUnreadableFile() {
        Locale locale = Locale.US;
        Path path = Paths.get("src/test/resources/unreadable_file.txt");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertEquals("Path should be an existing readable file: \"src/test/resources/unreadable_file.txt\"", exception.getMessage());
    }

    @Test
    void testAddPathMalformedURL() {
        Locale locale = Locale.US;
        Path path = Paths.get("invalid://path");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, path));
        assertTrue(exception.getMessage().startsWith("Failed to read \"invalid://path\""));
    }
}
