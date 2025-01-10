
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FakeValuesService_addPathTest {

    @Test
    void testAddPath_ValidPath() throws MalformedURLException {
        // Given
        FakeValuesService fakeValuesService = new FakeValuesService();
        Locale locale = Locale.ENGLISH;
        Path validPath = Paths.get("validFile.txt");
        when(Files.exists(validPath)).thenReturn(true);
        when(Files.isDirectory(validPath)).thenReturn(false);
        when(Files.isReadable(validPath)).thenReturn(true);

        // When
        fakeValuesService.addPath(locale, validPath);

        // Then (no exception is thrown)
    }

    @Test
    void testAddPath_InvalidPath() {
        // Given
        FakeValuesService fakeValuesService = new FakeValuesService();
        Locale locale = Locale.ENGLISH;
        Path invalidPath = Paths.get("invalidFile.txt");
        when(Files.exists(invalidPath)).thenReturn(false);

        // When and Then
        assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, invalidPath));
    }

    @Test
    void testAddPath_MalformedURL() {
        // Given
        FakeValuesService fakeValuesService = new FakeValuesService();
        Locale locale = Locale.ENGLISH;
        Path validPath = Paths.get("validFile.txt");
        when(Files.exists(validPath)).thenReturn(true);
        when(Files.isDirectory(validPath)).thenReturn(false);
        when(Files.isReadable(validPath)).thenReturn(true);
        doThrow(new MalformedURLException()).when(validPath).toUri().toURL();

        // When and Then
        assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addPath(locale, validPath));
    }
}
