
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Options_toStringTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testToStringWithNoOptions() {
        String expected = "[ Options: [ short {} ] [ long {} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithShortOptions() {
        options.addOption("a", "description for a");
        options.addOption("b", "description for b");
        String expected = "[ Options: [ short {a=description for a, b=description for b} ] [ long {} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithLongOptions() {
        options.addOption(null, "long-only1", false, "description for long-only1");
        options.addOption(null, "long-only2", false, "description for long-only2");
        String expected = "[ Options: [ short {} ] [ long {long-only1=description for long-only1, long-only2=description for long-only2} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithBothShortAndLongOptions() {
        options.addOption("a", "long-a", false, "description for a");
        options.addOption("b", "long-b", false, "description for b");
        String expected = "[ Options: [ short {a=description for a, b=description for b} ] [ long {long-a=description for a, long-b=description for b} ]";
        assertEquals(expected, options.toString());
    }
}
