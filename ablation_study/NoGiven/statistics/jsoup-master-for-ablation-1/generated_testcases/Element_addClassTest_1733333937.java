
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_addClassTest {

    @Test
    void testAddClass() {
        Element element = new Element("div");
        Set<String> mockClasses = mock(Set.class);
        when(mockClasses.add("testClass")).thenReturn(true);
        element.classNames(mockClasses);

        Element result = element.addClass("testClass");

        verify(mockClasses).add("testClass");
        assertEquals(element, result);
    }

    @Test
    void testAddClassWithNull() {
        Element element = new Element("div");
        assertThrows(IllegalArgumentException.class, () -> {
            element.addClass(null);
        });
    }
}
