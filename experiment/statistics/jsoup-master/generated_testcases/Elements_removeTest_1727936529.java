
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_removeTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div id='1'></div><div id='2'></div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testRemove() {
        elements.remove();
        assertEquals(0, elements.size());
    }
}
