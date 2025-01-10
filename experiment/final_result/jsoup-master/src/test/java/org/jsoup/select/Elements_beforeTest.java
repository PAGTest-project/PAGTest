
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_beforeTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>First</p><p>Second</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testBefore() {
        elements.before("<span>Before</span>");
        assertEquals("<span>Before</span><p>First</p><span>Before</span><p>Second</p>", elements.parents().first().html().replaceAll("\\s+", ""));
    }
}
