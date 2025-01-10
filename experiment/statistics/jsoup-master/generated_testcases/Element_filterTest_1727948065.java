
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.NodeFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Element_filterTest {

    private Element element;

    @BeforeEach
    public void setUp() {
        String html = "<body><div><p>One</p></div><div><p>Two</p></div><div>Three</div></body>";
        Document doc = Jsoup.parse(html);
        element = doc.selectFirst("div");
        assertNotNull(element);
    }

    @Test
    public void testFilter() {
        NodeFilter nodeFilter = new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                if (node instanceof Element && ((Element) node).tagName().equals("p")) {
                    return FilterResult.STOP;
                }
                return FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        };

        Element filteredElement = element.filter(nodeFilter);
        assertNotNull(filteredElement);
        assertEquals("p", filteredElement.tagName());
        assertEquals("One", filteredElement.text());
    }
}
