
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.ast.Op;
import net.hydromatic.morel.eval.Code;
import net.hydromatic.morel.eval.Codes;
import net.hydromatic.morel.eval.Unit;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Compiler_compileTest {

    @Test
    public void testCompile() {
        Compiler compiler = new Compiler(new TypeSystem());
        Context cx = new Context(Codes.emptyEnv());

        // Test BOOL_LITERAL
        Core.Exp boolExp = new Core.Literal(Op.BOOL_LITERAL, true);
        Code boolCode = compiler.compile(cx, boolExp);
        assertEquals(Codes.constant(true), boolCode);

        // Test CHAR_LITERAL
        Core.Exp charExp = new Core.Literal(Op.CHAR_LITERAL, 'a');
        Code charCode = compiler.compile(cx, charExp);
        assertEquals(Codes.constant('a'), charCode);

        // Test INT_LITERAL
        Core.Exp intExp = new Core.Literal(Op.INT_LITERAL, 42);
        Code intCode = compiler.compile(cx, intExp);
        assertEquals(Codes.constant(42), intCode);

        // Test REAL_LITERAL
        Core.Exp realExp = new Core.Literal(Op.REAL_LITERAL, 3.14f);
        Code realCode = compiler.compile(cx, realExp);
        assertEquals(Codes.constant(3.14f), realCode);

        // Test STRING_LITERAL
        Core.Exp stringExp = new Core.Literal(Op.STRING_LITERAL, "test");
        Code stringCode = compiler.compile(cx, stringExp);
        assertEquals(Codes.constant("test"), stringCode);

        // Test UNIT_LITERAL
        Core.Exp unitExp = new Core.Literal(Op.UNIT_LITERAL, Unit.INSTANCE);
        Code unitCode = compiler.compile(cx, unitExp);
        assertEquals(Codes.constant(Unit.INSTANCE), unitCode);

        // Test FN_LITERAL
        Core.Exp fnExp = new Core.Literal(Op.FN_LITERAL, new BuiltIn("test"));
        Code fnCode = compiler.compile(cx, fnExp);
        assertEquals(Codes.constant(Codes.BUILT_IN_VALUES.get(new BuiltIn("test"))), fnCode);

        // Test INTERNAL_LITERAL
        Core.Exp internalExp = new Core.Literal(Op.INTERNAL_LITERAL, new Object());
        Code internalCode = compiler.compile(cx, internalExp);
        assertEquals(Codes.constant(new Object()), internalCode);

        // Test VALUE_LITERAL
        Core.Exp valueExp = new Core.Literal(Op.VALUE_LITERAL, new Object());
        Code valueCode = compiler.compile(cx, valueExp);
        assertEquals(Codes.constant(new Object()), valueCode);

        // Test UNHANDLED_OP
        Core.Exp unhandledExp = new Core.Exp(Op.UNHANDLED_OP) {};
        assertThrows(AssertionError.class, () -> compiler.compile(cx, unhandledExp));
    }
}
