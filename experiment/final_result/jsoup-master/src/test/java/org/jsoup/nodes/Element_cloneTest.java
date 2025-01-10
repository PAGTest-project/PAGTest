
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_cloneTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
        element.appendChild(new Element("p").text("Child 1"));
        element.appendChild(new Element("p").text("Child 2"));
    }

    @Test
    public void testClone() {
        Element clonedElement = element.clone();

        // Assert that the cloned element is not the same instance
        assertNotSame(element, clonedElement);

        // Assert that the cloned element has the same tag name
        assertEquals(element.tagName(), clonedElement.tagName());

        // Assert that the cloned element has the same number of child nodes
        assertEquals(element.childNodeSize(), clonedElement.childNodeSize());

        // Assert that the cloned element has the same child nodes
        for (int i = 0; i < element.childNodeSize(); i++) {
            assertEquals(element.child(i).outerHtml(), clonedElement.child(i).outerHtml());
        }
    }
}
