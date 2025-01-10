
package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class StreamParser_selectFirstTest {
    private StreamParser streamParser;

    @BeforeEach
    public void setUp() {
        streamParser = new StreamParser(Jsoup.parse(""));
    }

    @Test
    public void testSelectFirstWithExistingMatch() throws IOException {
        String html = "<html><body><p>First</p><p>Second</p></body></html>";
        streamParser.parse(new StringReader(html), "http://example.com");

        Evaluator eval = new Evaluator.Tag("p");
        Element first = streamParser.selectFirst(eval);

        assertNotNull(first);
        assertEquals("First", first.text());
    }

    @Test
    public void testSelectFirstWithNoMatch() throws IOException {
        String html = "<html><body><div>First</div><div>Second</div></body></html>";
        streamParser.parse(new StringReader(html), "http://example.com");

        Evaluator eval = new Evaluator.Tag("p");
        Element first = streamParser.selectFirst(eval);

        assertNull(first);
    }

    @Test
    public void testSelectFirstWithPartialParse() throws IOException {
        String html = "<html><body><p>First</p><p>Second</p></body></html>";
        streamParser.parse(new StringReader(html), "http://example.com");

        // Simulate partial parse by stopping the parser
        streamParser.stop();

        Evaluator eval = new Evaluator.Tag("p");
        Element first = streamParser.selectFirst(eval);

        assertNotNull(first);
        assertEquals("First", first.text());
    }

    @Test
    public void testSelectFirstWithFragmentParse() throws IOException {
        String html = "<p>First</p><p>Second</p>";
        streamParser.parseFragment(new StringReader(html), null, "http://example.com");

        Evaluator eval = new Evaluator.Tag("p");
        Element first = streamParser.selectFirst(eval);

        assertNotNull(first);
        assertEquals("First", first.text());
    }

    @Test
    public void testSelectFirstWithIOException() {
        String html = "<html><body><p>First</p><p>Second</p></body></html>";
        streamParser.parse(new StringReader(html), "http://example.com");

        Evaluator eval = new Evaluator.Tag("p");
        assertThrows(IOException.class, () -> {
            // Simulate an IOException by closing the reader
            streamParser.close();
            streamParser.selectFirst(eval);
        });
    }
}
