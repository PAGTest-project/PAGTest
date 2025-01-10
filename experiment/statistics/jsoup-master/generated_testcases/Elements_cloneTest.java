
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_cloneTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div id='test'><p>Hello</p></div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testClone() {
        Elements clonedElements = elements.clone();

        // Verify the clone has the same size
        assertEquals(elements.size(), clonedElements.size());

        // Verify the clone contains the same elements
        for (int i = 0; i < elements.size(); i++) {
            assertEquals(elements.get(i).outerHtml(), clonedElements.get(i).outerHtml());
        }
    }
}
