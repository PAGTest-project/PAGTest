
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

public class Element_forEachTest {
    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div><p>1</p><p>2</p><p>3</p></div>");
    }

    @Test
    public void testForEach() {
        Element div = doc.select("div").first();
        AtomicInteger count = new AtomicInteger();
        div.children().forEach(element -> count.incrementAndGet());
        assertEquals(3, count.get());
    }

    @Test
    public void testForEachWithEmptyElement() {
        Element emptyDiv = doc.select("div").first().empty();
        AtomicInteger count = new AtomicInteger();
        emptyDiv.children().forEach(element -> count.incrementAndGet());
        assertEquals(0, count.get());
    }
}
