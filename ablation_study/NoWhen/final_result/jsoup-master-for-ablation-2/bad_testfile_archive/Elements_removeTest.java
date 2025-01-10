
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_removeTest {

    @Test
    public void testRemove() {
        // Given
        String html = "<div><p>Paragraph 1</p><p>Paragraph 2</p></div>";
        Elements elements = new Elements(Jsoup.parse(html).select("p"));

        // When
        elements.remove();

        // Then
        assertEquals(0, elements.size());
        assertFalse(Jsoup.parse(html).select("p").isEmpty()); // Ensure original HTML is unchanged
    }
}
