
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.StringTokenizer;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumerationUtils_toListTest {

    @Test
    public void testToListWithStringTokenizer() {
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        List<String> result = EnumerationUtils.toList(tokenizer);
        assertEquals(3, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));
    }
}
