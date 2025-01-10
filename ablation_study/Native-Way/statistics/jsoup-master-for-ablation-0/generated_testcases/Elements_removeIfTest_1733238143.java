
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Elements_removeIfTest {

    @Test
    public void testRemoveIf_NoElementsRemoved() {
        Elements elements = new Elements(Arrays.asList(
                new Element("div"),
                new Element("span")
        ));

        Predicate<Element> filter = el -> el.tagName().equals("p");

        boolean result = elements.removeIf(filter);

        assertFalse(result);
    }

    @Test
    public void testRemoveIf_ElementsRemoved() {
        Elements elements = new Elements(Arrays.asList(
                new Element("div"),
                new Element("span"),
                new Element("p")
        ));

        Predicate<Element> filter = el -> el.tagName().equals("p");

        boolean result = elements.removeIf(filter);

        assertTrue(result);
    }
}
