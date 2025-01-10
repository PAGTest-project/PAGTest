
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.function.UnaryOperator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_replaceAllTest {

    @Test
    public void testReplaceAll() {
        Elements elements = new Elements();
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.add(element1);
        elements.add(element2);

        UnaryOperator<Element> operator = e -> new Element("p");

        elements.replaceAll(operator);

        assertEquals("p", elements.get(0).tagName());
        assertEquals("p", elements.get(1).tagName());
    }
}
