
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
        String expected = "[ Options: [ short {a=[ Option a :: description for a :: class java.lang.String ], b=[ Option b :: description for b :: class java.lang.String ]} ] [ long {} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithLongOptions() {
        options.addOption(Option.builder("c").longOpt("long-c").desc("description for c").build());
        options.addOption(Option.builder("d").longOpt("long-d").desc("description for d").build());
        String expected = "[ Options: [ short {c=[ Option c long-c :: description for c :: class java.lang.String ], d=[ Option d long-d :: description for d :: class java.lang.String ]} ] [ long {long-c=[ Option c long-c :: description for c :: class java.lang.String ], long-d=[ Option d long-d :: description for d :: class java.lang.String ]} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithBothShortAndLongOptions() {
        options.addOption("e", "description for e");
        options.addOption(Option.builder("f").longOpt("long-f").desc("description for f").build());
        String expected = "[ Options: [ short {e=[ Option e :: description for e :: class java.lang.String ], f=[ Option f long-f :: description for f :: class java.lang.String ]} ] [ long {long-f=[ Option f long-f :: description for f :: class java.lang.String ]} ]";
        assertEquals(expected, options.toString());
    }
}
