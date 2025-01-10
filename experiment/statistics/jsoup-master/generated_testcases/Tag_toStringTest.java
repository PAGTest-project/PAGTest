
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tag_toStringTest {

    @Test
    public void testToString() {
        Tag tag = Tag.valueOf("div");
        assertEquals("div", tag.toString());
    }
}
