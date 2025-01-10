
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_parentsTest {

    @Test
    void testParents() {
        // Given
        Element element = mock(Element.class);
        Element parent1 = mock(Element.class);
        Element parent2 = mock(Element.class);
        Element root = mock(Element.class);

        when(element.parent()).thenReturn(parent1);
        when(parent1.parent()).thenReturn(parent2);
        when(parent2.parent()).thenReturn(root);
        when(root.parent()).thenReturn(null);

        when(parent1.nameIs("#root")).thenReturn(false);
        when(parent2.nameIs("#root")).thenReturn(false);
        when(root.nameIs("#root")).thenReturn(true);

        // When
        Elements parents = element.parents();

        // Then
        assertEquals(2, parents.size());
        assertTrue(parents.contains(parent1));
        assertTrue(parents.contains(parent2));
    }
}
