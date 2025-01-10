
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
        element = doc.body();
    }

    @Test
    public void testClosestWithMatchingEvaluator() {
        Evaluator evaluator = new Evaluator() {
            @Override
            public boolean matches(Element root, Element element) {
                return "div".equals(element.tagName());
            }
        };

        Element result = element.child(0).closest(evaluator);
        assertNotNull(result);
        assertEquals("div", result.tagName());
    }

    @Test
    public void testClosestWithNonMatchingEvaluator() {
        Evaluator evaluator = new Evaluator() {
            @Override
            public boolean matches(Element root, Element element) {
                return "nonexistent".equals(element.tagName());
            }
        };

        Element result = element.child(0).closest(evaluator);
        assertNull(result);
    }

    @Test
    public void testClosestWithNullEvaluator() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.child(0).closest(null);
        });
    }
}
