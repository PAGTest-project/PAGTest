
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_addClassTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div class='old'></div><div></div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testAddClass() {
        elements.addClass("newClass");
        for (Element element : elements) {
            assertTrue(element.hasClass("newClass"));
        }
    }
}
