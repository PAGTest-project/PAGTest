
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Element_getElementsByAttributeValueMatchingTest {

    @Test
    public void testGetElementsByAttributeValueMatching() {
        // Given
        Element element = new Element("div");
        Pattern pattern = Pattern.compile("value");

        // When
        Elements result = element.getElementsByAttributeValueMatching("key", pattern);

        // Then
        assertNotNull(result);
    }
}
