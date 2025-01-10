
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_strTest {

    @Test
    public void testStr() {
        StringBuilder b = new StringBuilder("test");
        String result = Static.str(b);
        assertEquals("test", result);
        assertEquals(0, b.length());
    }
}
