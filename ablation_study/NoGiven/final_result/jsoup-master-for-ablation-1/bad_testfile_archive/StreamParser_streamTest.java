
package org.jsoup.parser;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StreamParser_streamTest {

    @Test
    public void testStream() {
        // Given
        StreamParser parser = new StreamParser(Parser.htmlParser());
        parser.parse("<html><body><div></div></body></html>", "http://example.com");

        // When
        Stream<Element> elementStream = parser.stream();

        // Then
        assertNotNull(elementStream);
    }
}
