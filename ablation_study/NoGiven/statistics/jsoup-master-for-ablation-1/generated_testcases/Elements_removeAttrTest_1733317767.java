
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_removeAttrTest {

    @Test
    void testRemoveAttr() {
        // Given
        String html = "<div class='test' id='testId'></div>";
        Elements elements = new Elements(Jsoup.parse(html).body().children());
        String attributeKey = "class";

        // When
        elements.removeAttr(attributeKey);

        // Then
        assertFalse(elements.hasAttr(attributeKey));
    }
}
