
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_toStringTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = Option.builder("o").desc("option description").build();
    }

    @Test
    public void testToStringBasic() {
        String expected = "[ Option o :: option description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithLongOption() {
        option = Option.builder("o").longOpt("option").desc("option description").build();
        String expected = "[ Option o  option :: option description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithDeprecated() {
        option = Option.builder("o").desc("option description").deprecated().build();
        String expected = "[ Option o Deprecated :: option description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithArg() {
        option = Option.builder("o").desc("option description").hasArg().build();
        String expected = "[ Option o [ARG] :: option description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithArgs() {
        option = Option.builder("o").desc("option description").hasArgs().build();
        String expected = "[ Option o [ARG...] :: option description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithOptionalArg() {
        option = Option.builder("o").desc("option description").hasArg().optionalArg(true).build();
        String expected = "[ Option o [ARG] :: option description :: class java.lang.String ]";
        assertEquals(expected, option.toString());
    }

    @Test
    public void testToStringWithType() {
        option = Option.builder("o").desc("option description").type(Integer.class).build();
        String expected = "[ Option o :: option description :: class java.lang.Integer ]";
        assertEquals(expected, option.toString());
    }
}
