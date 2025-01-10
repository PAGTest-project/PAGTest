
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tag_hashCodeTest {

    @Test
    public void testHashCode() {
        Tag tag1 = Tag.valueOf("div", Parser.NamespaceHtml, ParseSettings.preserveCase);
        Tag tag2 = Tag.valueOf("div", Parser.NamespaceHtml, ParseSettings.preserveCase);

        assertEquals(tag1.hashCode(), tag2.hashCode());
    }
}
