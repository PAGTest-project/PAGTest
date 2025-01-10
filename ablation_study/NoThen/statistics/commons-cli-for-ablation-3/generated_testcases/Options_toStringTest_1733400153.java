
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
        String expected = "[ Options: [ short [] ] [ long [] ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithShortOptions() {
        options.addOption("a", "description for a");
        options.addOption("b", "description for b");
        String expected = "[ Options: [ short [a, b] ] [ long [] ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithLongOptions() {
        options.addOption(Option.builder("c").longOpt("long-c").desc("description for c").build());
        options.addOption(Option.builder("d").longOpt("long-d").desc("description for d").build());
        String expected = "[ Options: [ short [c, d] ] [ long [long-c, long-d] ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithBothShortAndLongOptions() {
        options.addOption("e", "description for e");
        options.addOption(Option.builder("f").longOpt("long-f").desc("description for f").build());
        String expected = "[ Options: [ short [e, f] ] [ long [long-f] ]";
        assertEquals(expected, options.toString());
    }
}
