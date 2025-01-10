
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Enumeration;
import java.util.Vector;

import org.junit.jupiter.api.Test;

public class EnumerationUtils_getTest {

    @Test
    public void testGetValidIndex() {
        final Vector<String> vector = new Vector<>();
        vector.addElement("zero");
        vector.addElement("one");
        vector.addElement("two");
        final Enumeration<String> en = vector.elements();

        assertEquals("one", EnumerationUtils.get(en, 1));
    }

    @Test
    public void testGetInvalidIndex() {
        final Vector<String> vector = new Vector<>();
        vector.addElement("zero");
        vector.addElement("one");
        final Enumeration<String> en = vector.elements();

        assertThrows(IndexOutOfBoundsException.class, () -> EnumerationUtils.get(en, 2));
    }
}
