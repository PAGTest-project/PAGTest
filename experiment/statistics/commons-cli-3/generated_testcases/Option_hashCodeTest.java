
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Option_hashCodeTest {

    @Test
    public void testHashCode() {
        Option option1 = Option.builder("opt1").longOpt("longOpt1").build();
        Option option2 = Option.builder("opt1").longOpt("longOpt1").build();
        Option option3 = Option.builder("opt2").longOpt("longOpt2").build();

        assertEquals(option1.hashCode(), option2.hashCode());
        assertEquals(option1.hashCode(), option1.hashCode());
        assertEquals(option2.hashCode(), option2.hashCode());

        assertEquals(option3.hashCode(), option3.hashCode());
    }
}
