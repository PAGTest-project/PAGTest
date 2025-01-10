
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StreamParser_parseTest {

    @Test
    public void testParse() {
        StreamParser parser = new StreamParser(Parser.htmlParser());
        String input = "<html><body><p>Test</p></body></html>";
        String baseUri = "http://example.com";

        StreamParser result = parser.parse(new StringReader(input), baseUri);

        assertNotNull(result.document());
    }
}
