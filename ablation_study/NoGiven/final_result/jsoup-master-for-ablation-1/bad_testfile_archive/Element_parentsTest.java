
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_parentsTest {

    @Test
    void testParents() {
        // Given
        Element child = new Element("div");
        Element parent1 = new Element("div");
        Element parent2 = new Element("div");
        Element root = new Element("div");
        root.attr("id", "root");

        child.parentNode = parent1;
        parent1.parentNode = parent2;
        parent2.parentNode = root;

        // When
        Elements parents = child.parents();

        // Then
        assertEquals(2, parents.size());
        assertEquals(parent1, parents.get(0));
        assertEquals(parent2, parents.get(1));
    }
}
