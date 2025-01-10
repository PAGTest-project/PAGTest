
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Objects;

public class Tag_hashCodeTest {

    @Test
    public void testHashCode() {
        Tag tag = Tag.valueOf("p", Parser.NamespaceHtml, ParseSettings.preserveCase);
        int expectedHashCode = Objects.hash("p", false, false, false, false, false, false, false);
        assertEquals(expectedHashCode, tag.hashCode());
    }
}
