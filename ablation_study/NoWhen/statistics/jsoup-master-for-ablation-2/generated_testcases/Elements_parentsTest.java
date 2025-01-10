
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_parentsTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        String html = "<div id='parent'><div id='child'></div></div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("#child"));
    }

    @Test
    public void testParents() {
        Elements parents = elements.parents();
        assertEquals(1, parents.size());
        assertEquals("parent", parents.first().id());
    }

    @Test
    public void testParentsWithMultipleChildren() {
        String html = "<div id='parent'><div id='child1'></div><div id='child2'></div></div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("div[id^=child]"));

        Elements parents = elements.parents();
        assertEquals(1, parents.size());
        assertEquals("parent", parents.first().id());
    }

    @Test
    public void testParentsWithNoParents() {
        String html = "<div id='root'></div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("#root"));

        Elements parents = elements.parents();
        assertEquals(0, parents.size());
    }

    @Test
    public void testParentsWithNestedStructure() {
        String html = "<div id='grandparent'><div id='parent'><div id='child'></div></div></div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("#child"));

        Elements parents = elements.parents();
        assertEquals(2, parents.size());
        Set<String> parentIds = new HashSet<>();
        parents.forEach(parent -> parentIds.add(parent.id()));
        assertTrue(parentIds.contains("parent"));
        assertTrue(parentIds.contains("grandparent"));
    }
}
