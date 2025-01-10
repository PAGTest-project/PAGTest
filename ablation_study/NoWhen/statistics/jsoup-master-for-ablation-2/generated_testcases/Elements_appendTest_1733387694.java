
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
        Document doc = Jsoup.parse("<div><p>Hello</p></div>");
        Elements elements = doc.select("p");
        String htmlToAppend = "<span>World</span>";

        // When
        elements.append(htmlToAppend);

        // Then
        assertEquals("<p>Hello<span>World</span></p>", elements.html());
    }
}
