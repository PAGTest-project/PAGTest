
package org.jsoup.nodes;

import org.jsoup.select.Evaluator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_closestTest {

    @Test
    void testClosest_MatchFound() {
        Element root = new Element("root");
        Element child = new Element("child");
        root.appendChild(child);

        Evaluator evaluator = new Evaluator() {
            @Override
            public boolean matches(Element root, Element element) {
                return "child".equals(element.tagName());
            }
        };

        Element result = child.closest(evaluator);
        assertNotNull(result);
        assertEquals("child", result.tagName());
    }

    @Test
    void testClosest_NoMatchFound() {
        Element root = new Element("root");
        Element child = new Element("child");
        root.appendChild(child);

        Evaluator evaluator = new Evaluator() {
            @Override
            public boolean matches(Element root, Element element) {
                return "nonexistent".equals(element.tagName());
            }
        };

        Element result = child.closest(evaluator);
        assertNull(result);
    }
}
