
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_toStringTest {
    private Option option;

    @BeforeEach
    public void setUp() {
        option = Option.builder("o").longOpt("option").desc("option description").build();
    }

    @Test
    public void testToStringWithShortOption() {
        option.clearValues();
        assertEquals("[ Option o :: option description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithLongOption() {
        option.clearValues();
        option = Option.builder("o").longOpt("option").desc("option description").hasArg().build();
        option.processValue("value");
        assertEquals("[ Option o  'option' [ARG] :: option description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithDeprecatedOption() {
        option.clearValues();
        option = Option.builder("o").longOpt("option").desc("option description")
                .deprecated(DeprecatedAttributes.builder().setForRemoval(true).setSince("2.0").setDescription("Use X.").get())
                .build();
        assertEquals("[ Option o  'option'  'Deprecated for removal since 2.0: Use X.' :: option description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithArgs() {
        option.clearValues();
        option = Option.builder("o").longOpt("option").desc("option description").hasArgs().build();
        option.processValue("value1");
        option.processValue("value2");
        assertEquals("[ Option o  'option' [ARG...] :: option description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithOptionalArg() {
        option.clearValues();
        option = Option.builder("o").longOpt("option").desc("option description").optionalArg(true).build();
        option.processValue("value");
        assertEquals("[ Option o  'option' [ARG] :: option description :: class java.lang.String ]", option.toString());
    }
}
