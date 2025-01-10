
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Enumeration;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class EnumerationUtils_getTest {

    @Test
    public void testGet_ValidIndex() {
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        vector.add("C");
        Enumeration<String> enumeration = vector.elements();

        String result = EnumerationUtils.get(enumeration, 1);
        assertEquals("B", result);
    }

    @Test
    public void testGet_InvalidIndex() {
        Vector<String> vector = new Vector<>();
        vector.add("A");
        Enumeration<String> enumeration = vector.elements();

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            EnumerationUtils.get(enumeration, 1);
        });
        assertEquals("Entry does not exist: 1", exception.getMessage());
    }
}
