
package net.hydromatic.morel.ast;

import com.google.common.collect.ImmutableList;
import net.hydromatic.morel.type.Binding;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FromBuilder_yield_Test {

    @Test
    void testYield_TUPLE_IDENTITY_Singleton() {
        FromBuilder builder = new FromBuilder(null, null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id(Core.Op.ID, new Core.IdPat("x"))));
        builder.yield_(false, ImmutableList.of(new Binding(new Core.IdPat("x"), null, null, false)), exp);
        assertEquals(1, builder.steps.size());
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_TUPLE_IDENTITY_NonSingleton() {
        FromBuilder builder = new FromBuilder(null, null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id(Core.Op.ID, new Core.IdPat("x")), new Core.Id(Core.Op.ID, new Core.IdPat("y"))));
        builder.yield_(false, ImmutableList.of(new Binding(new Core.IdPat("x"), null, null, false), new Binding(new Core.IdPat("y"), null, null, false)), exp);
        assertEquals(0, builder.steps.size());
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_TUPLE_RENAME_Singleton() {
        FromBuilder builder = new FromBuilder(null, null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id(Core.Op.ID, new Core.IdPat("y"))));
        builder.yield_(false, ImmutableList.of(new Binding(new Core.IdPat("x"), null, null, false)), exp);
        assertEquals(1, builder.steps.size());
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_TUPLE_RENAME_NonSingleton() {
        FromBuilder builder = new FromBuilder(null, null);
        Core.Exp exp = new Core.Tuple(Core.Op.TUPLE, ImmutableList.of(new Core.Id(Core.Op.ID, new Core.IdPat("y")), new Core.Id(Core.Op.ID, new Core.IdPat("z"))));
        builder.yield_(false, ImmutableList.of(new Binding(new Core.IdPat("x"), null, null, false), new Binding(new Core.IdPat("y"), null, null, false)), exp);
        assertEquals(1, builder.steps.size());
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }

    @Test
    void testYield_ID_Singleton() {
        FromBuilder builder = new FromBuilder(null, null);
        Core.Exp exp = new Core.Id(Core.Op.ID, new Core.IdPat("x"));
        builder.yield_(false, ImmutableList.of(new Binding(new Core.IdPat("x"), null, null, false)), exp);
        assertEquals(0, builder.steps.size());
        assertEquals(Integer.MIN_VALUE, builder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, builder.removeIfLastIndex);
    }
}
