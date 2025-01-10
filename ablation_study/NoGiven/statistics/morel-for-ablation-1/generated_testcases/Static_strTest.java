
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Static_strTest {

    @Test
    void testStr() {
        StringBuilder b = new StringBuilder("test");
        String result = Static.str(b);
        assertEquals("test", result);
        assertEquals(0, b.length());
    }
}
