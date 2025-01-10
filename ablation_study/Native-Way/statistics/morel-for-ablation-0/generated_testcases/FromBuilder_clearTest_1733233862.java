
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FromBuilder_clearTest {

    @Test
    void testClear() {
        FromBuilder fromBuilder = new FromBuilder(new TypeSystem(), null);
        fromBuilder.scan(new Core.IdPat("test"));
        fromBuilder.clear();

        assertEquals(0, fromBuilder.bindings().size());
        assertEquals(Integer.MIN_VALUE, fromBuilder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, fromBuilder.removeIfLastIndex);
    }
}
