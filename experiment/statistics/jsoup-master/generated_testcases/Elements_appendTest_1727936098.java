
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_appendTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>First</p><p>Second</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testAppend() {
        elements.append("<span>Appended</span>");
        assertEquals("<p>First<span>Appended</span></p><p>Second<span>Appended</span></p>", elements.outerHtml());
    }
}
