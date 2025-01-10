
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentType_nodeNameTest {

    @Test
    public void testNodeName() {
        DocumentType documentType = new DocumentType("html", "-//IETF//DTD HTML//", "");
        assertEquals("#doctype", documentType.nodeName());
    }
}
