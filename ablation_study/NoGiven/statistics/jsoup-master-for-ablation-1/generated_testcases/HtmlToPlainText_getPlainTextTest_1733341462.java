
package org.jsoup.examples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtmlToPlainText_getPlainTextTest {

    @Test
    public void testGetPlainText() {
        // Given
        String html = "<html><body><p>Hello, World!</p></body></html>";
        Document doc = Jsoup.parse(html);
        Element element = doc.body().child(0);

        // When
        HtmlToPlainText formatter = new HtmlToPlainText();
        String plainText = formatter.getPlainText(element);

        // Then
        assertEquals("Hello, World!", plainText);
    }
}
