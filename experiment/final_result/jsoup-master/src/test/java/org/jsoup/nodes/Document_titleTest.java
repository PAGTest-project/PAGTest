
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Document_titleTest {

    @Test
    public void testTitle_withTitleElement() {
        // Given
        Document doc = Jsoup.parse("<html><head><title>Test Title</title></head><body></body></html>");

        // When
        String title = doc.title();

        // Then
        assertEquals("Test Title", title);
    }

    @Test
    public void testTitle_withoutTitleElement() {
        // Given
        Document doc = Jsoup.parse("<html><head></head><body></body></html>");

        // When
        String title = doc.title();

        // Then
        assertEquals("", title);
    }
}
