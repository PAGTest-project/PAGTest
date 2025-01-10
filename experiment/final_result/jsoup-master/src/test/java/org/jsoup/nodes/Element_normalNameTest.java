
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Element_normalNameTest {

    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element(Tag.valueOf("div"), "");
    }

    @Test
    public void testNormalName() {
        String expectedNormalName = "div";
        assertEquals(expectedNormalName, element.normalName());
    }
}
