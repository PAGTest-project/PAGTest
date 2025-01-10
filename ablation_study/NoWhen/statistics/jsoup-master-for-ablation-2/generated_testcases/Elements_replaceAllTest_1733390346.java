
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_replaceAllTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div id='1'>One</div><div id='2'>Two</div><div id='3'>Three</div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testReplaceAllWithIdentityOperator() {
        UnaryOperator<Element> identityOperator = UnaryOperator.identity();
        elements.replaceAll(identityOperator);
        assertEquals(3, elements.size());
        assertEquals("One", elements.get(0).text());
        assertEquals("Two", elements.get(1).text());
        assertEquals("Three", elements.get(2).text());
    }

    @Test
    public void testReplaceAllWithTextModificationOperator() {
        UnaryOperator<Element> textModificationOperator = element -> {
            element.text("Modified");
            return element;
        };
        elements.replaceAll(textModificationOperator);
        assertEquals(3, elements.size());
        assertEquals("Modified", elements.get(0).text());
        assertEquals("Modified", elements.get(1).text());
        assertEquals("Modified", elements.get(2).text());
    }

    @Test
    public void testReplaceAllWithAttributeModificationOperator() {
        UnaryOperator<Element> attributeModificationOperator = element -> {
            element.attr("data-test", "true");
            return element;
        };
        elements.replaceAll(attributeModificationOperator);
        assertEquals(3, elements.size());
        assertTrue(elements.get(0).hasAttr("data-test"));
        assertTrue(elements.get(1).hasAttr("data-test"));
        assertTrue(elements.get(2).hasAttr("data-test"));
    }

    @Test
    public void testReplaceAllWithElementRemovalOperator() {
        UnaryOperator<Element> elementRemovalOperator = element -> {
            if (element.id().equals("2")) {
                return null;
            }
            return element;
        };
        elements.replaceAll(elementRemovalOperator);
        assertEquals(2, elements.size());
        assertEquals("One", elements.get(0).text());
        assertEquals("Three", elements.get(1).text());
    }
}
