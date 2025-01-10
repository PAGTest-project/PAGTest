
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_afterTest {

    @Test
    public void testAfter() {
        // Given
        Document doc = Jsoup.parse("<div><p>Hello</p></div>");
        Elements elements = doc.select("p");
        String html = "<span>World</span>";

        // When
        elements.after(html);

        // Then
        assertEquals("<p>Hello</p><span>World</span>", elements.outerHtml());
    }
}
