
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_closestTest {

    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div id='root'><div id='child1'><div id='child2'></div></div></div>");
    }

    @Test
    public void testClosestWithMatchingEvaluator() {
        Element child2 = doc.select("#child2").first();
        Evaluator evaluator = new Evaluator.Id("root");
        Element closest = child2.closest(evaluator);
        assertNotNull(closest);
        assertEquals("root", closest.id());
    }

    @Test
    public void testClosestWithNonMatchingEvaluator() {
        Element child2 = doc.select("#child2").first();
        Evaluator evaluator = new Evaluator.Id("nonexistent");
        Element closest = child2.closest(evaluator);
        assertNull(closest);
    }

    @Test
    public void testClosestWithNullEvaluator() {
        Element child2 = doc.select("#child2").first();
        assertThrows(IllegalArgumentException.class, () -> {
            child2.closest(null);
        });
    }
}
