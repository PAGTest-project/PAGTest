
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
    public void testToStringWithNoOptions() {
        assertEquals("[]", optionGroup.toString());
    }

    @Test
    public void testToStringWithSingleOption() {
        Option option = Option.builder("a").desc("description").build();
        optionGroup.addOption(option);
        assertEquals("[-adescription]", optionGroup.toString().replace(" ", ""));
    }

    @Test
    public void testToStringWithMultipleOptions() {
        Option option1 = Option.builder("a").desc("description1").build();
        Option option2 = Option.builder("b").desc("description2").build();
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);
        assertEquals("[-adescription1, -bdescription2]", optionGroup.toString().replace(" ", ""));
    }

    @Test
    public void testToStringWithLongOption() {
        Option option = Option.builder().longOpt("long").desc("long description").build();
        optionGroup.addOption(option);
        assertEquals("[--longlong description]", optionGroup.toString().replace(" ", ""));
    }

    @Test
    public void testToStringWithMixedOptions() {
        Option option1 = Option.builder("a").desc("description1").build();
        Option option2 = Option.builder().longOpt("long").desc("long description").build();
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);
        assertEquals("[-adescription1, --longlong description]", optionGroup.toString().replace(" ", ""));
    }

    @Test
    public void testToStringWithNoDescription() {
        Option option = Option.builder("a").build();
        optionGroup.addOption(option);
        assertEquals("[-a]", optionGroup.toString());
    }
}
