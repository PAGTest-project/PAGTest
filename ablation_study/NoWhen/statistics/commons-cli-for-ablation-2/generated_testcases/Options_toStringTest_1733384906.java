
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        options.addOption("a", "first");
        options.addOption("b", "second");
        String expected = "[ Options: [ short {a=Option[a first], b=Option[b second]} ] [ long {} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithLongOptions() {
        options.addOption(null, "first", false, "first option");
        options.addOption(null, "second", false, "second option");
        String expected = "[ Options: [ short {} ] [ long {first=Option[first first option], second=Option[second second option]} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithBothShortAndLongOptions() {
        options.addOption("a", "first", false, "first option");
        options.addOption("b", "second", false, "second option");
        String expected = "[ Options: [ short {a=Option[a first first option], b=Option[b second second option]} ] [ long {first=Option[a first first option], second=Option[b second second option]} ]";
        assertEquals(expected, options.toString());
    }
}
