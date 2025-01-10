
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.*;

public class Element_forEachTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testForEach() {
        // Ensure child nodes are available
        element.appendChild(new Element("p"));
        element.appendChild(new Element("span"));

        // Test forEach method
        StringBuilder result = new StringBuilder();
        Consumer<Element> action = e -> result.append(e.tagName()).append(" ");
        element.forEach(action);

        assertEquals("p span ", result.toString());
    }

    @Test
    public void testForEachWithNoChildren() {
        // Ensure no child nodes are available
        StringBuilder result = new StringBuilder();
        Consumer<Element> action = e -> result.append(e.tagName()).append(" ");
        element.forEach(action);

        assertEquals("", result.toString());
    }
}
