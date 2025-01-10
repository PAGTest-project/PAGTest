
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

        parent1.appendChild(child);
        parent2.appendChild(parent1);
        root.appendChild(parent2);

        // When
        Elements parents = child.parents();

        // Then
        assertEquals(3, parents.size());
        assertEquals(parent1, parents.get(0));
        assertEquals(parent2, parents.get(1));
        assertEquals(root, parents.get(2));
    }
}
