
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_toStringTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = Option.builder("o")
                       .longOpt("option")
                       .desc("description")
                       .hasArg()
                       .type(String.class)
                       .build();
    }

    @Test
    public void testToStringWithShortOption() {
        option = Option.builder("o")
                       .desc("description")
                       .type(String.class)
                       .build();
        assertEquals("[ Option o :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithLongOption() {
        option = Option.builder("o")
                       .longOpt("long-option")
                       .desc("description")
                       .type(String.class)
                       .build();
        assertEquals("[ Option o long-option :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithDeprecated() {
        option = Option.builder("o")
                       .longOpt("option")
                       .desc("description")
                       .type(String.class)
                       .deprecated(DeprecatedAttributes.DEFAULT)
                       .build();
        assertEquals("[ Option o option Deprecated :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithArgument() {
        option = Option.builder("o")
                       .longOpt("option")
                       .desc("description")
                       .hasArg()
                       .type(String.class)
                       .build();
        assertEquals("[ Option o option [ARG] :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithMultipleArguments() {
        option = Option.builder("o")
                       .longOpt("option")
                       .desc("description")
                       .hasArgs()
                       .type(String.class)
                       .build();
        assertEquals("[ Option o option [ARG...] :: description :: class java.lang.String ]", option.toString());
    }
}
