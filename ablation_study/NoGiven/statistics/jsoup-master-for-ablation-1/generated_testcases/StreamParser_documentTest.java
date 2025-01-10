
package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class StreamParser_documentTest {
    private StreamParser streamParser;

    @BeforeEach
    public void setUp() {
        streamParser = new StreamParser(Jsoup.newParser());
    }

    @Test
    public void testDocumentAfterParse() {
        streamParser.parse(new StringReader("<html><head><title>Test</title></head><body></body></html>"), "http://example.com");
        Document document = streamParser.document();
        assertNotNull(document);
        assertEquals("Test", document.title());
    }

    @Test
    public void testDocumentBeforeParse() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            streamParser.document();
        });
        assertEquals("Must run parse() before calling.", exception.getMessage());
    }
}
