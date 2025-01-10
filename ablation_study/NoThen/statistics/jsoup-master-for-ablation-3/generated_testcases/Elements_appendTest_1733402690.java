
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_appendTest {

    @Test
    public void testAppend() {
        // Given
        Document doc = Jsoup.parse("<div><p></p></div>");
        Elements elements = doc.select("p");
        String html = "<span>Test</span>";

        // When
        elements.append(html);

        // Then
        assertEquals("<p><span>Test</span></p>", elements.first().outerHtml());
    }
}
