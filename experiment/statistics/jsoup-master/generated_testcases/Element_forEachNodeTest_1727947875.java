
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Element_forEachNodeTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3</div>");
        element = doc.body().child(0);
    }

    @Test
    public void testForEachNode() {
        AtomicInteger seenCount = new AtomicInteger();
        Consumer<Node> action = node -> seenCount.incrementAndGet();

        element.forEachNode(action);

        assertEquals(4, seenCount.get()); // div and its three p children
    }
}
