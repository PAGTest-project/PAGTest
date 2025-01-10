
package org.jsoup.parser;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class StreamParser_expectNextTest {

    @Test
    public void testExpectNext_ElementFound() throws IOException {
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse("<html><body><div id='test'>Test</div></body></html>", "http://example.com");

        Element result = parser.expectNext("#test");
        assertNotNull(result);
        assertEquals("test", result.id());
    }

    @Test
    public void testExpectNext_ElementNotFound() {
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse("<html><body><div id='test'>Test</div></body></html>", "http://example.com");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parser.expectNext("#nonexistent");
        });

        String expectedMessage = "No elements matched the query '#nonexistent' in the document.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
