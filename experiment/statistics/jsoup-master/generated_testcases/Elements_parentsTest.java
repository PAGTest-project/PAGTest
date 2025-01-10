
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_parentsTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p id='p1'></p><p id='p2'></p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testParents() {
        Elements parents = elements.parents();
        assertEquals(1, parents.size());
        assertEquals("div", parents.first().tagName());
    }
}
