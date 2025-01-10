
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_joiningTest {

    @Test
    public void testJoiningWithDelimiter() {
        List<CharSequence> strings = Arrays.asList("apple", "banana", "cherry");
        String result = strings.stream().collect(StringUtil.joining(", "));
        assertEquals("apple, banana, cherry", result);
    }

    @Test
    public void testJoiningWithEmptyList() {
        List<CharSequence> strings = Collections.emptyList();
        String result = strings.stream().collect(StringUtil.joining(", "));
        assertEquals("", result);
    }

    @Test
    public void testJoiningWithSingleElement() {
        List<CharSequence> strings = Collections.singletonList("apple");
        String result = strings.stream().collect(StringUtil.joining(", "));
        assertEquals("apple", result);
    }

    @Test
    public void testJoiningWithNullElement() {
        List<CharSequence> strings = Arrays.asList("apple", null, "cherry");
        String result = strings.stream().collect(StringUtil.joining(", "));
        assertEquals("apple, null, cherry", result);
    }

    @Test
    public void testJoiningWithWhitespaceDelimiter() {
        List<CharSequence> strings = Arrays.asList("apple", "banana", "cherry");
        String result = strings.stream().collect(StringUtil.joining(" "));
        assertEquals("apple banana cherry", result);
    }
}
