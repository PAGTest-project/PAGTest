
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Enumeration;
import java.util.Vector;
import static org.junit.jupiter.api.Assertions.*;

class EnumerationUtils_getTest {

    @Test
    void testGet_ValidIndex() {
        Vector<String> vector = new Vector<>();
        vector.add("first");
        vector.add("second");
        vector.add("third");
        Enumeration<String> enumeration = vector.elements();

        assertEquals("second", EnumerationUtils.get(enumeration, 1));
    }

    @Test
    void testGet_IndexOutOfBounds() {
        Vector<String> vector = new Vector<>();
        vector.add("first");
        Enumeration<String> enumeration = vector.elements();

        assertThrows(IndexOutOfBoundsException.class, () -> EnumerationUtils.get(enumeration, 1));
    }
}
