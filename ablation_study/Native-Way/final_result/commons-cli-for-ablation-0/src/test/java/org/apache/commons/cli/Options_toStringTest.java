
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
        options.addOption("a", "Option A");
        options.addOption("b", "Option B");
        String expected = "[ Options: [ short {a=[ Option a :: Option A :: class java.lang.String ], b=[ Option b :: Option B :: class java.lang.String ]} ] [ long {} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithLongOptions() {
        options.addOption(null, "foo", false, "Foo");
        options.addOption(null, "bar", false, "Bar");
        String expected = "[ Options: [ short {foo=[ Option null foo :: Foo :: class java.lang.String ], bar=[ Option null bar :: Bar :: class java.lang.String ]} ] [ long {foo=[ Option null foo :: Foo :: class java.lang.String ], bar=[ Option null bar :: Bar :: class java.lang.String ]} ]";
        assertEquals(expected, options.toString());
    }

    @Test
    public void testToStringWithBothShortAndLongOptions() {
        options.addOption("a", "foo", false, "Foo");
        options.addOption("b", "bar", false, "Bar");
        String expected = "[ Options: [ short {a=[ Option a foo :: Foo :: class java.lang.String ], b=[ Option b bar :: Bar :: class java.lang.String ]} ] [ long {foo=[ Option a foo :: Foo :: class java.lang.String ], bar=[ Option b bar :: Bar :: class java.lang.String ]} ]";
        assertEquals(expected, options.toString());
    }
}
