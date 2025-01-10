
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_toStringTest {

    private OptionGroup optionGroup;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testToStringWithShortOptions() {
        optionGroup.addOption(new Option("f", "foo", false, "Foo"));
        optionGroup.addOption(new Option("b", "bar", false, "Bar"));

        assertEquals("[-f Foo, -b Bar]", optionGroup.toString());
    }

    @Test
    public void testToStringWithLongOptions() {
        optionGroup.addOption(new Option(null, "foo", false, "Foo"));
        optionGroup.addOption(new Option(null, "bar", false, "Bar"));

        assertEquals("[--foo Foo, --bar Bar]", optionGroup.toString());
    }

    @Test
    public void testToStringWithMixedOptions() {
        optionGroup.addOption(new Option("f", "foo", false, "Foo"));
        optionGroup.addOption(new Option(null, "bar", false, "Bar"));

        assertEquals("[-f Foo, --bar Bar]", optionGroup.toString());
    }

    @Test
    public void testToStringWithNoOptions() {
        assertEquals("[]", optionGroup.toString());
    }

    @Test
    public void testToStringWithSelectedOption() {
        Option option1 = new Option("f", "foo", false, "Foo");
        Option option2 = new Option(null, "bar", false, "Bar");
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);
        optionGroup.setSelected(option1);

        assertEquals("[-f Foo, --bar Bar]", optionGroup.toString());
    }
}
