
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
        assertEquals("[ Option o 'option' :: option description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithDeprecatedOption() {
        option.clearValues();
        option.deprecated(DeprecatedAttributes.builder().setForRemoval(true).setSince("2.0").setDescription("Use X.").get());
        assertEquals("[ Option o 'option' ' Deprecated for removal since 2.0: Use X.' :: option description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithArgument() {
        option.clearValues();
        option.hasArg();
        option.processValue("argValue");
        assertEquals("[ Option o 'option' [ARG] :: option description :: class java.lang.String ]", option.toString());
    }

    @Test
    public void testToStringWithMultipleArguments() {
        option.clearValues();
        option.hasArgs();
        option.processValue("argValue1");
        option.processValue("argValue2");
        assertEquals("[ Option o 'option' [ARG...] :: option description :: class java.lang.String ]", option.toString());
    }
}
