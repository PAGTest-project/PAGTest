
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Document_nodeNameTest {

    @Test
    public void testNodeName() {
        Document doc = new Document("https://example.com/");
        assertEquals("#document", doc.nodeName());
    }
}
