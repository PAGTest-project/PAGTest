
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_removeAttrTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
        element.attr("testAttr", "testValue");
    }

    @Test
    public void testRemoveAttr() {
        // Given an element with an attribute
        assertTrue(element.hasAttr("testAttr"));

        // When removing the attribute
        Element result = element.removeAttr("testAttr");

        // Then the attribute should be removed
        assertFalse(result.hasAttr("testAttr"));
        assertSame(element, result);
    }
}
