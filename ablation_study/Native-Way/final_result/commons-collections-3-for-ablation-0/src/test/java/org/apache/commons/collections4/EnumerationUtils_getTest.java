
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Enumeration;
import java.util.Vector;
import static org.junit.jupiter.api.Assertions.*;

class EnumerationUtils_getTest {

    @Test
    void testGetValidIndex() {
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        vector.add("C");
        Enumeration<String> enumeration = vector.elements();

        assertEquals("B", EnumerationUtils.get(enumeration, 1));
    }

    @Test
    void testGetInvalidIndex() {
        Vector<String> vector = new Vector<>();
        vector.add("A");
        Enumeration<String> enumeration = vector.elements();

        assertThrows(IndexOutOfBoundsException.class, () -> EnumerationUtils.get(enumeration, 1));
    }
}
