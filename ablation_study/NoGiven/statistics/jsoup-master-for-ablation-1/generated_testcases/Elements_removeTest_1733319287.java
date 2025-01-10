
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_removeTest {
    private Document doc;
    private Elements elements;

    @BeforeEach
    public void setUp() {
        String html = "<div><p>Paragraph 1</p><p>Paragraph 2</p></div>";
        doc = Jsoup.parse(html);
        elements = doc.select("p");
    }

    @Test
    public void testRemove() {
        // When
        elements.remove();

        // Then
        assertEquals("", doc.body().html());
    }

    @Test
    public void testRemoveWithEmpty() {
        // When
        elements.empty().remove();

        // Then
        assertEquals("", doc.body().html());
    }

    @Test
    public void testRemoveWithUnwrap() {
        // When
        elements.unwrap().remove();

        // Then
        assertEquals("<div>Paragraph 1Paragraph 2</div>", doc.body().html());
    }
}
