
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.type.Type;

class FromBuilder_clearTest {

    @Test
    void testClear() {
        FromBuilder fromBuilder = new FromBuilder(new TypeSystem(), null);
        fromBuilder.scan(new Core.IdPat(new Type(), "test", 0));
        fromBuilder.clear();

        assertEquals(0, fromBuilder.bindings().size());
    }
}
