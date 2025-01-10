
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
        Elements elements = Jsoup.parse(html).select("p");

        // When
        elements.remove();

        // Then
        assertEquals(0, elements.size());
        assertEquals("<div></div>", elements.parents().first().outerHtml());
    }
}
