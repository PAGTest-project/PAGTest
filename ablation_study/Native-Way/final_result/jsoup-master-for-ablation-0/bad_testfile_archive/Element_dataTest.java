
package org.jsoup.nodes;

import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Element_dataTest {

    @Test
    void testData() {
        Element element = new Element("div");
        StringBuilder sb = new StringBuilder();
        DataNode dataNode = mock(DataNode.class);
        Comment comment = mock(Comment.class);
        CDataNode cDataNode = mock(CDataNode.class);

        when(dataNode.getWholeData()).thenReturn("data");
        when(comment.getData()).thenReturn("comment");
        when(cDataNode.getWholeText()).thenReturn("cdata");

        element.appendChild(dataNode);
        element.appendChild(comment);
        element.appendChild(cDataNode);

        String result = element.data();

        assertEquals("datacommentcdata", result);
    }
}
