
package org.jsoup.helper;

import org.jsoup.parser.Parser;
import org.jsoup.parser.StreamParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DataUtil_streamParserTest {

    @TempDir
    Path tempDir;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createFile(tempDir.resolve("test.html"));
        Files.write(tempFile, "<html><head><meta charset='UTF-8'></head><body></body></html>".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void testStreamParserWithValidCharset() throws IOException {
        StreamParser streamParser = DataUtil.streamParser(tempFile, StandardCharsets.UTF_8, "http://example.com", Parser.htmlParser());
        assertNotNull(streamParser);
        // Additional assertions can be added to verify the state of the StreamParser
    }

    @Test
    public void testStreamParserWithNullCharset() throws IOException {
        StreamParser streamParser = DataUtil.streamParser(tempFile, null, "http://example.com", Parser.htmlParser());
        assertNotNull(streamParser);
        // Additional assertions can be added to verify the state of the StreamParser
    }

    @Test
    public void testStreamParserWithInvalidPath() {
        Path invalidPath = tempDir.resolve("nonexistent.html");
        assertThrows(IOException.class, () -> DataUtil.streamParser(invalidPath, StandardCharsets.UTF_8, "http://example.com", Parser.htmlParser()));
    }
}
