
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_lastElementChildTest {

    @Test
    void testLastElementChild() {
        // Given
        Element parent = new Element("div");
        Element child1 = new Element("span");
        Element child2 = new Element("p");
        Element child3 = new Element("a");
        parent.appendChild(child1);
        parent.appendChild(child2);
        parent.appendChild(child3);

        // When
        Element lastElementChild = parent.lastElementChild();

        // Then
        assertEquals(child3, lastElementChild);
    }

    @Test
    void testLastElementChildWithNonElementChildren() {
        // Given
        Element parent = new Element("div");
        Element child1 = new Element("span");
        TextNode textNode = new TextNode("text");
        Element child2 = new Element("p");
        parent.appendChild(child1);
        parent.appendChild(textNode);
        parent.appendChild(child2);

        // When
        Element lastElementChild = parent.lastElementChild();

        // Then
        assertEquals(child2, lastElementChild);
    }

    @Test
    void testLastElementChildWithNoElementChildren() {
        // Given
        Element parent = new Element("div");
        TextNode textNode1 = new TextNode("text1");
        TextNode textNode2 = new TextNode("text2");
        parent.appendChild(textNode1);
        parent.appendChild(textNode2);

        // When
        Element lastElementChild = parent.lastElementChild();

        // Then
        assertNull(lastElementChild);
    }
}
