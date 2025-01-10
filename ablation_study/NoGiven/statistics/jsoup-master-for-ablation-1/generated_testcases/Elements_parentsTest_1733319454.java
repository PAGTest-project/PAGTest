
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Elements_parentsTest {

    @Test
    public void testParents() {
        // Given
        Element mockElement1 = mock(Element.class);
        Element mockElement2 = mock(Element.class);
        Elements elements = new Elements(mockElement1, mockElement2);

        HashSet<Element> parents1 = new LinkedHashSet<>();
        parents1.add(mock(Element.class));
        parents1.add(mock(Element.class));

        HashSet<Element> parents2 = new LinkedHashSet<>();
        parents2.add(mock(Element.class));

        when(mockElement1.parents()).thenReturn(new Elements(parents1));
        when(mockElement2.parents()).thenReturn(new Elements(parents2));

        // When
        Elements result = elements.parents();

        // Then
        assertEquals(3, result.size());
    }
}
