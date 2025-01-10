
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
    public void testToStringWithShortOptionOnly() {
        option = Option.builder("o").desc("description").build();
        assertEquals("[ Option o :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithLongOption() {
        assertEquals("[ Option o 'option' :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithDeprecated() {
        option = Option.builder("o").desc("description").deprecated().build();
        assertEquals("[ Option o Deprecated :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithArgs() {
        option = Option.builder("o").desc("description").hasArg().build();
        assertEquals("[ Option o [ARG] :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithMultipleArgs() {
        option = Option.builder("o").desc("description").hasArgs().build();
        assertEquals("[ Option o [ARG...] :: description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithType() {
        option = Option.builder("o").desc("description").type(Integer.class).build();
        assertEquals("[ Option o :: description :: class java.lang.Integer ]", option.toString());
    }

    @Test
    public void testToStringWithAllAttributes() {
        option = Option.builder("o").longOpt("option").desc("description").hasArg().type(Integer.class).deprecated().build();
        assertEquals("[ Option o 'option' Deprecated [ARG] :: description :: class java.lang.Integer ]", option.toString());
    }
}
