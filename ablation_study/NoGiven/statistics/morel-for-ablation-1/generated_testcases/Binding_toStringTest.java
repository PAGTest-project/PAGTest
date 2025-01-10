
package net.hydromatic.morel.type;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.eval.Unit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Binding_toStringTest {

    @Test
    public void testToStringWithExp() {
        Core.NamedPat id = new Core.NamedPat("x", null);
        Core.Exp exp = new Core.Exp() {};
        Binding binding = Binding.of(id, exp);
        assertEquals("x = " + exp, binding.toString());
    }

    @Test
    public void testToStringWithValueUnit() {
        Core.NamedPat id = new Core.NamedPat("y", null);
        Binding binding = Binding.of(id);
        assertEquals("y : " + id.type.moniker(), binding.toString());
    }

    @Test
    public void testToStringWithValueNonUnit() {
        Core.NamedPat id = new Core.NamedPat("z", null);
        Object value = new Object();
        Binding binding = Binding.of(id, value);
        assertEquals("z = " + value + " : " + id.type.moniker(), binding.toString());
    }
}
