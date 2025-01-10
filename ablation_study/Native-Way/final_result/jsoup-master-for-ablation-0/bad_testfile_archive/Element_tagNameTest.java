
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_tagNameTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div", "http://example.com");
    }

    @Test
    public void testTagNameWithValidInputs() {
        element.tagName("span", "http://example.com");
        assertEquals("span", element.tag().getName());
        assertEquals("http://example.com", element.tag().getNamespace());
    }

    @Test
    public void testTagNameWithEmptyTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.tagName("", "http://example.com");
        });
    }

    @Test
    public void testTagNameWithEmptyNamespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.tagName("span", "");
        });
    }

    @Test
    public void testTagNameWithNullTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.tagName(null, "http://example.com");
        });
    }

    @Test
    public void testTagNameWithNullNamespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.tagName("span", null);
        });
    }
}
