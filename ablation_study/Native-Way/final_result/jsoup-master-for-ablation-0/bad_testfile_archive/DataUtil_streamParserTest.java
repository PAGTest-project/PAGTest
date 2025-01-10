
package org.jsoup.helper;

import org.jsoup.parser.Parser;
import org.jsoup.parser.StreamParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class DataUtil_streamParserTest {
    private Path testFilePath;
    private String baseUri;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        testFilePath = Paths.get("src/test/resources/test.html");
        baseUri = "http://example.com";
        parser = Parser.htmlParser();
    }

    @Test
    public void testStreamParserWithCharset() throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        StreamParser streamParser = DataUtil.streamParser(testFilePath, charset, baseUri, parser);
        assertNotNull(streamParser);
    }

    @Test
    public void testStreamParserWithoutCharset() throws IOException {
        StreamParser streamParser = DataUtil.streamParser(testFilePath, null, baseUri, parser);
        assertNotNull(streamParser);
    }

    @Test
    public void testStreamParserWithInvalidPath() {
        Path invalidPath = Paths.get("invalid/path/test.html");
        assertThrows(IOException.class, () -> {
            DataUtil.streamParser(invalidPath, StandardCharsets.UTF_8, baseUri, parser);
        });
    }

    @Test
    public void testStreamParserWithGzipFile() throws IOException {
        Path gzipFilePath = Paths.get("src/test/resources/test.html.gz");
        StreamParser streamParser = DataUtil.streamParser(gzipFilePath, StandardCharsets.UTF_8, baseUri, parser);
        assertNotNull(streamParser);
    }

    @Test
    public void testStreamParserWithBom() throws IOException {
        Path bomFilePath = Paths.get("src/test/resources/test_bom.html");
        StreamParser streamParser = DataUtil.streamParser(bomFilePath, null, baseUri, parser);
        assertNotNull(streamParser);
    }
}
