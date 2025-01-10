
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_ownTextTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("p");
    }

    @Test
    public void testOwnTextWithSingleTextNode() {
        element.appendText("Hello World");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithMultipleTextNodes() {
        element.appendText("Hello");
        element.appendText(" ");
        element.appendText("World");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithChildElements() {
        element.appendText("Hello");
        Element child = new Element("span");
        child.appendText("World");
        element.appendChild(child);
        assertEquals("Hello", element.ownText());
    }

    @Test
    public void testOwnTextWithWhitespace() {
        element.appendText("   Hello   ");
        assertEquals("Hello", element.ownText());
    }

    @Test
    public void testOwnTextWithNoText() {
        assertEquals("", element.ownText());
    }
}
