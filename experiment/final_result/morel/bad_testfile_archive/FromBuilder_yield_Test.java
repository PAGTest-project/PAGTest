
package net.hydromatic.morel.ast;

import com.google.common.collect.ImmutableList;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FromBuilder_yield_Test {

    @Test
    void testYield_TUPLE_IDENTITY_Singleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id(new Core.IdPat("x"))));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_TUPLE_IDENTITY_NonSingleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id(new Core.IdPat("x")), new Core.Id(new Core.IdPat("y"))));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        builder.bindings.add(new Binding(new Core.IdPat("y"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(builder, result);
    }

    @Test
    void testYield_TUPLE_RENAME_Singleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id(new Core.IdPat("y"))));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_TUPLE_RENAME_NonSingleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id(new Core.IdPat("y")), new Core.Id(new Core.IdPat("z"))));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        builder.bindings.add(new Binding(new Core.IdPat("y"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_ID_Singleton() {
        FromBuilder builder = new FromBuilder(new TypeSystem(), null);
        Core.Exp exp = new Core.Id(new Core.IdPat("x"));
        builder.bindings.add(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = builder.yield_(false, null, exp);
        assertEquals(builder, result);
    }
}
