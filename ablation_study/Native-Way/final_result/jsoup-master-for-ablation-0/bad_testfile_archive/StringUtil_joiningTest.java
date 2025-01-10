
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_joiningTest {

    @Test
    void testJoiningWithDelimiter() {
        String result = Stream.of("apple", "banana", "cherry")
                .collect(StringUtil.joining(", "));
        assertEquals("apple, banana, cherry", result);
    }

    @Test
    void testJoiningWithEmptyStream() {
        String result = Stream.of()
                .collect(StringUtil.joining(", "));
        assertEquals("", result);
    }

    @Test
    void testJoiningWithSingleElement() {
        String result = Stream.of("apple")
                .collect(StringUtil.joining(", "));
        assertEquals("apple", result);
    }

    @Test
    void testJoiningWithNullDelimiter() {
        String result = Stream.of("apple", "banana", "cherry")
                .collect(StringUtil.joining(null));
        assertEquals("applebananacherry", result);
    }

    @Test
    void testJoiningWithEmptyDelimiter() {
        String result = Stream.of("apple", "banana", "cherry")
                .collect(StringUtil.joining(""));
        assertEquals("applebananacherry", result);
    }
}
