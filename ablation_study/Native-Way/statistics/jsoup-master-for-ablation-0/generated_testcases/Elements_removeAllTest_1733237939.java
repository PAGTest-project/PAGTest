
package org.jsoup.select;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class Elements_removeAllTest {

    @Test
    void testRemoveAll() {
        Elements elements = new Elements(Arrays.asList(new Element("div"), new Element("span")));
        boolean result = elements.removeAll(Collections.singletonList(new Element("div")));
        assertTrue(result);
        assertEquals(1, elements.size());
        assertEquals("span", elements.get(0).tagName());
    }
}
