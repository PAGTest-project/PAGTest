
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Element_getElementsByAttributeStartingTest {

    @Test
    public void testGetElementsByAttributeStarting_ValidPrefix() {
        Element element = new Element("div");
        Elements result = element.getElementsByAttributeStarting("data-");
        assertNotNull(result);
    }

    @Test
    public void testGetElementsByAttributeStarting_EmptyPrefix() {
        Element element = new Element("div");
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByAttributeStarting("");
        });
    }
}
