
package org.jsoup.nodes;

import org.jsoup.SerializationException;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Attribute_htmlTest {

    @Test
    void testHtml_NormalCase() {
        Attribute attr = new Attribute("key", "value");
        String result = attr.html();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testHtml_IOException() {
        Attribute attr = new Attribute("key", "value") {
            @Override
            protected void html(Appendable accum, Document.OutputSettings out) throws IOException {
                throw new IOException("Test exception");
            }
        };
        assertThrows(SerializationException.class, () -> attr.html());
    }
}
