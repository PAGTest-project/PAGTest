
package org.jsoup.nodes;

import org.jsoup.Jsoup;
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
    public void testDataWithDataNode() {
        DataNode dataNode = new DataNode("sample data");
        element.appendChild(dataNode);
        assertEquals("sample data", element.data());
    }

    @Test
    public void testDataWithComment() {
        Comment comment = new Comment("sample comment");
        element.appendChild(comment);
        assertEquals("sample comment", element.data());
    }

    @Test
    public void testDataWithCDataNode() {
        CDataNode cDataNode = new CDataNode("sample cdata");
        element.appendChild(cDataNode);
        assertEquals("sample cdata", element.data());
    }

    @Test
    public void testDataWithMultipleNodes() {
        DataNode dataNode = new DataNode("data1");
        Comment comment = new Comment("comment1");
        CDataNode cDataNode = new CDataNode("cdata1");
        element.appendChild(dataNode);
        element.appendChild(comment);
        element.appendChild(cDataNode);
        assertEquals("data1comment1cdata1", element.data());
    }

    @Test
    public void testDataWithNoNodes() {
        assertEquals("", element.data());
    }
}
