
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Document_textTest {
    private Document document;

    @BeforeEach
    public void setUp() {
        document = new Document("http://example.com");
    }

    @Test
    public void testText() {
        String text = "Sample Text";
        document.text(text);
        assertEquals(text, document.text());
    }
}
