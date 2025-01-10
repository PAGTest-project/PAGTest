
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_filterTest {

    @Test
    public void testFilter() {
        // Given
        Elements elements = new Elements();
        elements.add(new Element("div"));
        NodeFilter nodeFilter = new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        };

        // When
        Elements filteredElements = elements.filter(nodeFilter);

        // Then
        assertEquals(elements, filteredElements);
    }
}
