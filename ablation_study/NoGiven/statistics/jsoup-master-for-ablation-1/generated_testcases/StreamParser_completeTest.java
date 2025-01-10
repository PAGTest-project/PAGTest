
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StreamParser_completeTest {

    @Test
    public void testComplete() throws IOException {
        // Given
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse("<html><head><title>Test</title></head><body><p>Hello</p></body></html>", "http://example.com");

        // When
        Document doc = parser.complete();

        // Then
        assertNotNull(doc);
    }
}
