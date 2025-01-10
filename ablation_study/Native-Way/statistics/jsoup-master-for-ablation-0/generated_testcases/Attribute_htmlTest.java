
package org.jsoup.nodes;

import org.jsoup.SerializationException;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class Attribute_htmlTest {

    @Test
    public void testHtml_NormalCase() {
        Attribute attr = new Attribute("key", "value");
        String result = attr.html();
        assertNotNull(result);
    }

    @Test
    public void testHtml_IOException() {
        Attribute attr = new Attribute("key", "value") {
            @Override
            protected void html(Appendable accum, Document.OutputSettings out) throws IOException {
                throw new IOException("Test exception");
            }
        };
        assertThrows(SerializationException.class, () -> attr.html());
    }
}
