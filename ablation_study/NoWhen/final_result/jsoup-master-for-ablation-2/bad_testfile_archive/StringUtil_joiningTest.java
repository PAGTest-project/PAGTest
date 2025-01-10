
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_joiningTest {

    @Test
    public void testJoiningWithDelimiter() {
        Stream<CharSequence> stream = Stream.of("apple", "banana", "cherry");
        String result = stream.collect(StringUtil.joining(", "));
        assertEquals("apple, banana, cherry", result);
    }

    @Test
    public void testJoiningWithEmptyStream() {
        Stream<CharSequence> stream = Stream.of();
        String result = stream.collect(StringUtil.joining(", "));
        assertEquals("", result);
    }

    @Test
    public void testJoiningWithSingleElement() {
        Stream<CharSequence> stream = Stream.of("apple");
        String result = stream.collect(StringUtil.joining(", "));
        assertEquals("apple", result);
    }

    @Test
    public void testJoiningWithNullElement() {
        Stream<CharSequence> stream = Stream.of("apple", null, "cherry");
        String result = stream.collect(StringUtil.joining(", "));
        assertEquals("apple, null, cherry", result);
    }

    @Test
    public void testJoiningWithWhitespaceNormalization() {
        Stream<CharSequence> stream = Stream.of("  apple  ", "  banana  ", "  cherry  ");
        String result = stream.collect(StringUtil.joining(", "));
        assertEquals("  apple  ,   banana  ,   cherry  ", result);
    }
}
