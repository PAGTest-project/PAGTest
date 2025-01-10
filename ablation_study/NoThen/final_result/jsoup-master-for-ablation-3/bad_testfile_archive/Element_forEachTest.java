
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

public class Element_forEachTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>1</p><p>2</p><p>3</p></div>");
        element = doc.body().child(0);
    }

    @Test
    public void testForEach() {
        AtomicInteger seenCount = new AtomicInteger();
        element.children().forEach(el -> seenCount.incrementAndGet());
        assertEquals(3, seenCount.get());
    }

    @Test
    public void testForEachWithEmptyChildren() {
        Element emptyElement = new Element("div");
        AtomicInteger seenCount = new AtomicInteger();
        emptyElement.children().forEach(el -> seenCount.incrementAndGet());
        assertEquals(0, seenCount.get());
    }
}
