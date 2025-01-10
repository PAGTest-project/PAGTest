
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementsMatchingTextTest {
    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div>One</div><div>Two</div><div>Three</div>");
    }

    @Test
    public void testGetElementsMatchingTextValidRegex() {
        Elements elements = doc.select("div:matchesOwn(One)");
        assertEquals(1, elements.size());
        assertEquals("One", elements.get(0).text());
    }

    @Test
    public void testGetElementsMatchingTextInvalidRegex() {
        assertThrows(PatternSyntaxException.class, () -> {
            Pattern.compile("[invalid regex");
        });
    }

    @Test
    public void testGetElementsMatchingTextNoMatch() {
        Elements elements = doc.select("div:matchesOwn(Four)");
        assertEquals(0, elements.size());
    }

    @Test
    public void testGetElementsMatchingTextCaseInsensitive() {
        Elements elements = doc.select("div:matchesOwn((?i)one)");
        assertEquals(1, elements.size());
        assertEquals("One", elements.get(0).text());
    }

    @Test
    public void testGetElementsMatchingTextMultipleMatches() {
        Document doc = Jsoup.parse("<div>One</div><div>One</div><div>Two</div>");
        Elements elements = doc.select("div:matchesOwn(One)");
        assertEquals(2, elements.size());
        assertEquals("One", elements.get(0).text());
        assertEquals("One", elements.get(1).text());
    }
}
