
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_removeTest {

    @Test
    public void testRemove() {
        // Given
        String html = "<div><p>One</p><p>Two</p></div>";
        Element parent = Jsoup.parse(html).select("div").first();
        Elements elements = parent.select("p");

        // When
        elements.remove();

        // Then
        assertEquals(0, elements.size());
        assertEquals("<div></div>", parent.outerHtml());
    }
}
