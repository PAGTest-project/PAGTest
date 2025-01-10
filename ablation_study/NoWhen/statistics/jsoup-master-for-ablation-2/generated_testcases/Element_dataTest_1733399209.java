
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_dataTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testDataNode() {
        DataNode dataNode = new DataNode("sample data");
        element.appendChild(dataNode);
        assertEquals("sample data", element.data());
    }

    @Test
    public void testCommentNode() {
        Comment commentNode = new Comment("sample comment");
        element.appendChild(commentNode);
        assertEquals("sample comment", element.data());
    }

    @Test
    public void testCDataNode() {
        CDataNode cDataNode = new CDataNode("sample cdata");
        element.appendChild(cDataNode);
        assertEquals("sample cdata", element.data());
    }

    @Test
    public void testMixedNodes() {
        DataNode dataNode = new DataNode("data1");
        Comment commentNode = new Comment("comment1");
        CDataNode cDataNode = new CDataNode("cdata1");
        element.appendChild(dataNode);
        element.appendChild(commentNode);
        element.appendChild(cDataNode);
        assertEquals("data1comment1cdata1", element.data());
    }

    @Test
    public void testNoNodes() {
        assertEquals("", element.data());
    }
}
