
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_wrapTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testWrap() {
        // Given
        element.append("<p>Existing Content</p>");

        // When
        Element wrappedElement = element.wrap("<div class='wrapper'></div>");

        // Then
        assertEquals("<div class='wrapper'><div><p>Existing Content</p></div></div>", wrappedElement.parent().outerHtml().replaceAll("\\s+", ""));
    }
}
