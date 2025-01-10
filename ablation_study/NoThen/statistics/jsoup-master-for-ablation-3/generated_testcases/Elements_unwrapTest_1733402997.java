
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_unwrapTest {

    @Test
    public void testUnwrap() {
        // Given
        Document doc = Jsoup.parse("<div><p>Hello</p></div>");
        Elements elements = doc.select("p");
        elements.wrap("<div class='wrapper'></div>");

        // When
        elements.unwrap();

        // Then
        assertEquals("<div>Hello</div>", doc.body().html());
    }
}
