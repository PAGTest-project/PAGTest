
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Element_getElementsByAttributeStartingTest {

    @Test
    public void testGetElementsByAttributeStarting() {
        // Given
        Element element = new Element("div");
        element.attr("data-test", "value");

        // When
        Elements result = element.getElementsByAttributeStarting("data-");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("div", result.first().tagName());
    }
}
