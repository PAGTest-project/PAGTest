
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.StringTokenizer;
import org.junit.jupiter.api.Test;

public class EnumerationUtils_toListTest {

    @Test
    public void testToListWithStringTokenizer() {
        StringTokenizer stringTokenizer = new StringTokenizer("This is a test");
        List<String> result = EnumerationUtils.toList(stringTokenizer);
        assertEquals(4, result.size());
        assertEquals("This", result.get(0));
        assertEquals("is", result.get(1));
        assertEquals("a", result.get(2));
        assertEquals("test", result.get(3));
    }
}
