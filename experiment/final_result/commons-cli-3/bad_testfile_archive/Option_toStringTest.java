
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_toStringTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = Option.builder("o").longOpt("option").desc("description").build();
    }

    @Test
    public void testToStringBasic() {
        String expected = "[ Option o option :: description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithLongOption() {
        option = Option.builder("o").longOpt("longOption").desc("description").build();
        String expected = "[ Option o longOption :: description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithDeprecated() {
        option = Option.builder("o").longOpt("longOption").desc("description").deprecated().build();
        String expected = "[ Option o longOption Deprecated :: description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithArgs() {
        option = Option.builder("o").longOpt("longOption").desc("description").hasArg().build();
        String expected = "[ Option o longOption [ARG] :: description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithUnlimitedArgs() {
        option = Option.builder("o").longOpt("longOption").desc("description").hasArgs().build();
        String expected = "[ Option o longOption [ARG...] :: description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }
}
