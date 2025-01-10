
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Element_cssSelectorTest {

    @Test
    public void testCssSelector_WithUniqueId() {
        Document doc = Jsoup.parse("<div id='unique'></div>");
        Element el = doc.select("div").first();
        assertEquals("#unique", el.cssSelector());
    }

    @Test
    public void testCssSelector_WithoutUniqueId() {
        Document doc = Jsoup.parse("<div></div>");
        Element el = doc.select("div").first();
        assertEquals("div", el.cssSelector());
    }

    @Test
    public void testCssSelector_WithNonUniqueId() {
        Document doc = Jsoup.parse("<div id='notUnique'></div><div id='notUnique'></div>");
        Element el = doc.select("div").first();
        assertEquals("div", el.cssSelector());
    }

    @Test
    public void testCssSelector_WithNullOwnerDocument() {
        Element el = new Element("div").attr("id", "unique");
        assertEquals("#unique", el.cssSelector());
    }
}
