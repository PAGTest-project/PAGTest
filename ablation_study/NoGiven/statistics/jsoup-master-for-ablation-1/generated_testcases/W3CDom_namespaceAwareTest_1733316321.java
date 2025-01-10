
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_namespaceAwareTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    void testNamespaceAwareTrue() throws XPathExpressionException {
        assertTrue(w3cDom.namespaceAware());

        String html = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        Document dom = w3cDom.fromJsoup(Jsoup.parse(html));
        NodeList nodeList = xpath(dom, "//*[local-name()='body']");
        assertEquals("body", nodeList.item(0).getLocalName());
    }

    @Test
    void testNamespaceAwareFalse() throws XPathExpressionException {
        w3cDom.namespaceAware(false);
        assertFalse(w3cDom.namespaceAware());

        String html = "<html xmlns='http://www.w3.org/1999/xhtml'><body id='One'><div>hello</div></body></html>";
        Document dom = w3cDom.fromJsoup(Jsoup.parse(html));
        NodeList nodeList = xpath(dom, "//body");
        assertEquals("body", nodeList.item(0).getLocalName());
    }

    private NodeList xpath(Document doc, String xpathExpr) throws XPathExpressionException {
        return (NodeList) XPathFactory.newInstance().newXPath().compile(xpathExpr).evaluate(doc, XPathConstants.NODESET);
    }
}
