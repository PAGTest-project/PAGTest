
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_toStringTest {

    @Test
    public void testToString() {
        Attribute attr = new Attribute("key", "value &");
        assertEquals("key=\"value &amp;\"", attr.toString());
    }
}
