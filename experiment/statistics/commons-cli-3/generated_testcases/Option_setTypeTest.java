
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_setTypeTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = new Option("f", null);
    }

    @Test
    public void testSetTypeWithClass() {
        option.setType(Integer.class);
        assertEquals(Integer.class, option.getType());
    }

    @Test
    public void testSetTypeWithObject() {
        option.setType((Object) Integer.class);
        assertEquals(Integer.class, option.getType());
    }
}
