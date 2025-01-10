
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorUtils_toStringTest {

    @Test
    public void testToStringWithNonEmptyIterator() {
        List<String> list = Arrays.asList("one", "two", "three");
        Iterator<String> iterator = list.iterator();
        String result = IteratorUtils.toString(iterator);
        assertEquals("[one, two, three]", result);
    }

    @Test
    public void testToStringWithEmptyIterator() {
        List<String> list = Arrays.asList();
        Iterator<String> iterator = list.iterator();
        String result = IteratorUtils.toString(iterator);
        assertEquals("[]", result);
    }

    @Test
    public void testToStringWithSingleElementIterator() {
        List<String> list = Arrays.asList("single");
        Iterator<String> iterator = list.iterator();
        String result = IteratorUtils.toString(iterator);
        assertEquals("[single]", result);
    }
}
