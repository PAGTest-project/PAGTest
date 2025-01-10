
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_removeAttrTest {

    @Test
    public void testRemoveAttr() {
        // Given
        Elements elements = new Elements();
        Element element1 = Jsoup.parse("<div id='test' class='test-class'></div>").select("div").first();
        Element element2 = Jsoup.parse("<div id='test2' class='test-class'></div>").select("div").first();
        elements.add(element1);
        elements.add(element2);

        // When
        elements.removeAttr("class");

        // Then
        assertFalse(element1.hasAttr("class"));
        assertFalse(element2.hasAttr("class"));
    }
}
