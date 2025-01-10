
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_dataTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>1<p>2<p>3</div>");
        element = doc.body().child(0);
    }

    @Test
    public void testDataWithDataNode() {
        element.append("<script>var a = 1;</script>");
        String result = element.data();
        assertEquals("var a = 1;", result);
    }

    @Test
    public void testDataWithComment() {
        element.append("<!-- comment -->");
        String result = element.data();
        assertEquals(" comment ", result);
    }

    @Test
    public void testDataWithCDataNode() {
        element.append("<![CDATA[cdata]]>");
        String result = element.data();
        assertEquals("cdata", result);
    }

    @Test
    public void testDataWithMixedNodes() {
        element.append("<script>var a = 1;</script><!-- comment --><![CDATA[cdata]]>");
        String result = element.data();
        assertEquals("var a = 1; comment cdata", result);
    }

    @Test
    public void testDataWithNoDataNodes() {
        String result = element.data();
        assertEquals("", result);
    }
}
