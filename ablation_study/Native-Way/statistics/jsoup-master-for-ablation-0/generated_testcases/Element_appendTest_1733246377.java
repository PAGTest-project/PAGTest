
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.helper.ValidationException;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        Document doc = new Document("https://example.com/");
        element = doc.body().appendElement("div");
    }

    @Test
    public void testAppendValidHtml() {
        String html = "<p>Hello, World!</p>";
        element.append(html);
        assertEquals(html, element.html());
    }

    @Test
    public void testAppendNullHtml() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.append(null);
        });
    }

    @Test
    public void testAppendEmptyHtml() {
        String html = "";
        element.append(html);
        assertEquals(html, element.html());
    }

    @Test
    public void testAppendComplexHtml() {
        String html = "<div><p>Paragraph 1</p><p>Paragraph 2</p></div>";
        element.append(html);
        assertEquals(html, element.html());
    }

    @Test
    public void testAppendHtmlWithBaseUri() {
        String html = "<a href='/page'>Link</a>";
        element.append(html);
        assertEquals(html, element.html());
        assertEquals("https://example.com/page", element.select("a").first().absUrl("href"));
    }
}
