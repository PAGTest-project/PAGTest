
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
    public void testDataWithDataNode() {
        DataNode dataNode = new DataNode("sampleData");
        element.appendChild(dataNode);
        assertEquals("sampleData", element.data());
    }

    @Test
    public void testDataWithComment() {
        Comment comment = new Comment("sampleComment");
        element.appendChild(comment);
        assertEquals("sampleComment", element.data());
    }

    @Test
    public void testDataWithCDataNode() {
        CDataNode cDataNode = new CDataNode("sampleCData");
        element.appendChild(cDataNode);
        assertEquals("sampleCData", element.data());
    }

    @Test
    public void testDataWithMixedNodes() {
        DataNode dataNode = new DataNode("data1");
        Comment comment = new Comment("comment1");
        CDataNode cDataNode = new CDataNode("cData1");
        element.appendChild(dataNode);
        element.appendChild(comment);
        element.appendChild(cDataNode);
        assertEquals("data1comment1cData1", element.data());
    }

    @Test
    public void testDataWithNoSpecialNodes() {
        TextNode textNode = new TextNode("text1");
        element.appendChild(textNode);
        assertEquals("", element.data());
    }
}
