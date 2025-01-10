
package net.hydromatic.morel.ast;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FromBuilder_yield_Test {

    @Test
    void testYield_TUPLE_IDENTITY_Singleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id("x")));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_TUPLE_IDENTITY_NonSingleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id("x"), new Core.Id("y")));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        builder.bindings.add(new Binding(new Core.IdPat("y"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(builder, result);
    }

    @Test
    void testYield_TUPLE_RENAME_Singleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id("y")));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_TUPLE_RENAME_NonSingleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id("y"), new Core.Id("z")));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        builder.bindings.add(new Binding(new Core.IdPat("y"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_ID_Singleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Id("x");
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(builder, result);
    }
}
