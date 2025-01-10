
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Element_forEachTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3</div>");
        element = doc.body().child(0);
    }

    @Test
    public void testForEach() {
        AtomicInteger seenCount = new AtomicInteger();
        Consumer<Element> action = e -> seenCount.incrementAndGet();

        element.forEach(action);

        assertEquals(3, seenCount.get()); // Should see 3 <p> elements
    }

    @Test
    public void testForEachWithEmptyElement() {
        Element emptyElement = new Element("div");
        AtomicInteger seenCount = new AtomicInteger();
        Consumer<Element> action = e -> seenCount.incrementAndGet();

        emptyElement.forEach(action);

        assertEquals(0, seenCount.get()); // Should see 0 elements
    }
}
