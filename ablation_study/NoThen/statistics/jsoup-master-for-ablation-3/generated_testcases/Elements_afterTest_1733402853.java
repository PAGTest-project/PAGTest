
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_afterTest {

    @Test
    public void testAfterWithSingleElement() {
        Elements elements = new Elements(new Element(Jsoup.parse("<div></div>").body().child(0)));
        elements.after("<p>Test</p>");
        assertEquals("<div></div><p>Test</p>", elements.outerHtml().trim());
    }

    @Test
    public void testAfterWithMultipleElements() {
        Elements elements = new Elements(Jsoup.parse("<div></div><div></div>").body().children());
        elements.after("<p>Test</p>");
        assertEquals("<div></div><p>Test</p><div></div><p>Test</p>", elements.outerHtml().trim());
    }
}
