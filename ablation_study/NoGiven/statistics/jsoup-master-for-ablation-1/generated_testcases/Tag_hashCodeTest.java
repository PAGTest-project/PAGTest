
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tag_hashCodeTest {

    @Test
    public void testHashCode() {
        Tag tag1 = Tag.valueOf("div");
        Tag tag2 = Tag.valueOf("div");
        Tag tag3 = Tag.valueOf("span");

        assertEquals(tag1.hashCode(), tag2.hashCode());
        assertNotEquals(tag1.hashCode(), tag3.hashCode(), "Hash codes for different tags should not be equal");
    }
}
