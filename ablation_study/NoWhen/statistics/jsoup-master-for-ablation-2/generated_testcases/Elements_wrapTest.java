
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_wrapTest {

    @Test
    public void testWrap() {
        // Given
        Document doc = Jsoup.parse("<div><p>Hello</p></div>");
        Elements elements = doc.select("p");
        String wrapHtml = "<span class='wrapper'></span>";

        // When
        elements.wrap(wrapHtml);

        // Then
        assertEquals("<span class=\"wrapper\"><p>Hello</p></span>", elements.get(0).outerHtml());
    }
}
