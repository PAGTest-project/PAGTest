
package org.jsoup.parser;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StreamParser_expectFirstTest {

    @Test
    public void testExpectFirst_ElementFound() throws IOException {
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse("<html><body><div id='test'></div></body></html>", "http://example.com");

        Element result = parser.expectFirst("#test");
        assertNotNull(result);
        assertEquals("test", result.id());
    }

    @Test
    public void testExpectFirst_ElementNotFound() {
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse("<html><body><div id='test'></div></body></html>", "http://example.com");

        Exception exception = assertThrows(IOException.class, () -> {
            parser.expectFirst("#nonexistent");
        });

        String expectedMessage = "No elements matched the query '#nonexistent' in the document.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
