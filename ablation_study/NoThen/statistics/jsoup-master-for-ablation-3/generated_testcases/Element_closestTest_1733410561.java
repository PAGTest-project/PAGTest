
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Evaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_closestTest {

    private Element element;

    @BeforeEach
    public void setUp() {
        String html = "<body><div><p>One</p></div><div><p>Two</p></div><div>Three</div></body>";
        Document doc = Jsoup.parse(html);
        element = doc.selectFirst("div");
    }

    @Test
    public void testClosestWithMatchingEvaluator() {
        Evaluator evaluator = new Evaluator.Tag("div");
        Element result = element.closest(evaluator);
        assertNotNull(result);
        assertEquals("div", result.tagName());
    }

    @Test
    public void testClosestWithNonMatchingEvaluator() {
        Evaluator evaluator = new Evaluator.Tag("span");
        Element result = element.closest(evaluator);
        assertNull(result);
    }

    @Test
    public void testClosestWithNullEvaluator() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.closest(null);
        });
    }

    @Test
    public void testClosestWithRootMatchingEvaluator() {
        Evaluator evaluator = new Evaluator.Tag("body");
        Element result = element.closest(evaluator);
        assertNotNull(result);
        assertEquals("body", result.tagName());
    }
}
