
package net.hydromatic.morel.type;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.eval.Unit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Binding_equalsTest {

    @Test
    void testEquals() {
        Core.NamedPat id1 = Core.NamedPat.of("id1");
        Core.NamedPat id2 = Core.NamedPat.of("id2");
        Core.Exp exp1 = Core.Exp.of("exp1");
        Core.Exp exp2 = Core.Exp.of("exp2");
        Object value1 = "value1";
        Object value2 = "value2";

        Binding binding1 = Binding.of(id1, exp1, value1);
        Binding binding2 = Binding.of(id1, exp1, value1);
        Binding binding3 = Binding.of(id2, exp2, value2);

        // Test same instance
        assertTrue(binding1.equals(binding1));

        // Test equal instances
        assertTrue(binding1.equals(binding2));
        assertTrue(binding2.equals(binding1));

        // Test different instances
        assertFalse(binding1.equals(binding3));
        assertFalse(binding3.equals(binding1));

        // Test null
        assertFalse(binding1.equals(null));

        // Test different class
        assertFalse(binding1.equals("not a Binding"));
    }
}
