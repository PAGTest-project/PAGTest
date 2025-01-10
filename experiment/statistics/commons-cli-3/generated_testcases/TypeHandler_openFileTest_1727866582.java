
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeHandler_openFileTest {

    @Test
    public void testOpenFileSuccess() throws ParseException {
        // Given a valid file path
        String validFilePath = "src/test/resources/validFile.txt";

        // When opening the file
        FileInputStream fis = TypeHandler.openFile(validFilePath);

        // Then the file input stream should be created
        // Note: We don't need to assert anything specific about the FileInputStream itself
        // as long as it is created without throwing an exception.
    }

    @Test
    public void testOpenFileFailure() {
        // Given an invalid file path
        String invalidFilePath = "nonExistentFile.txt";

        // When opening the file, it should throw a FileNotFoundException
        assertThrows(FileNotFoundException.class, () -> {
            TypeHandler.openFile(invalidFilePath);
        });
    }
}
