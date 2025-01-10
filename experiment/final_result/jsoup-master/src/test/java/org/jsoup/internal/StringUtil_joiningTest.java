
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtil_joiningTest {

    @Test
    public void testJoiningWithDelimiter() {
        List<CharSequence> strings = Arrays.asList("apple", "banana", "cherry");
        String result = strings.stream().collect(StringUtil.joining(", "));
        assertEquals("apple, banana, cherry", result);
    }

    @Test
    public void testJoiningWithEmptyList() {
        List<CharSequence> strings = List.of();
        String result = strings.stream().collect(StringUtil.joining(", "));
        assertEquals("", result);
    }

    @Test
    public void testJoiningWithSingleElement() {
        List<CharSequence> strings = Arrays.asList("apple");
        String result = strings.stream().collect(StringUtil.joining(", "));
        assertEquals("apple", result);
    }
}
