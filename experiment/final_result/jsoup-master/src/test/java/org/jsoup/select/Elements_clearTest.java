
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Elements_clearTest {

    @Test
    public void testClear() {
        // Given
        Elements elements = new Elements();
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.add(element1);
        elements.add(element2);

        // When
        elements.clear();

        // Then
        assertTrue(elements.isEmpty());
    }
}
