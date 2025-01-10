
package net.hydromatic.morel.eval;

import net.hydromatic.morel.ast.Core;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Closure_bindRecurseTest {

    @Test
    void testBindRecurse_ID_PAT() {
        Core.IdPat idPat = new Core.IdPat(Core.Op.ID_PAT, "x");
        assertTrue(Closure.bindRecurse(idPat, "value", (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_WILDCARD_PAT() {
        Core.WildcardPat wildcardPat = new Core.WildcardPat(Core.Op.WILDCARD_PAT);
        assertTrue(Closure.bindRecurse(wildcardPat, "value", (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_AS_PAT() {
        Core.AsPat asPat = new Core.AsPat(Core.Op.AS_PAT, new Core.IdPat(Core.Op.ID_PAT, "x"), "alias");
        assertTrue(Closure.bindRecurse(asPat, "value", (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_BOOL_LITERAL_PAT() {
        Core.LiteralPat literalPat = new Core.LiteralPat(Core.Op.BOOL_LITERAL_PAT, true);
        assertTrue(Closure.bindRecurse(literalPat, true, (namedPat, o) -> {}));
        assertFalse(Closure.bindRecurse(literalPat, false, (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_INT_LITERAL_PAT() {
        Core.LiteralPat literalPat = new Core.LiteralPat(Core.Op.INT_LITERAL_PAT, new BigDecimal(10));
        assertTrue(Closure.bindRecurse(literalPat, 10, (namedPat, o) -> {}));
        assertFalse(Closure.bindRecurse(literalPat, 20, (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_REAL_LITERAL_PAT() {
        Core.LiteralPat literalPat = new Core.LiteralPat(Core.Op.REAL_LITERAL_PAT, new BigDecimal(10.5));
        assertTrue(Closure.bindRecurse(literalPat, 10.5, (namedPat, o) -> {}));
        assertFalse(Closure.bindRecurse(literalPat, 20.5, (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_TUPLE_PAT() {
        Core.TuplePat tuplePat = new Core.TuplePat(Core.Op.TUPLE_PAT, Arrays.asList(
                new Core.LiteralPat(Core.Op.INT_LITERAL_PAT, new BigDecimal(1)),
                new Core.LiteralPat(Core.Op.INT_LITERAL_PAT, new BigDecimal(2))
        ));
        List<Object> listValue = Arrays.asList(1, 2);
        assertTrue(Closure.bindRecurse(tuplePat, listValue, (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_LIST_PAT() {
        Core.ListPat listPat = new Core.ListPat(Core.Op.LIST_PAT, Arrays.asList(
                new Core.LiteralPat(Core.Op.INT_LITERAL_PAT, new BigDecimal(1)),
                new Core.LiteralPat(Core.Op.INT_LITERAL_PAT, new BigDecimal(2))
        ));
        List<Object> listValue = Arrays.asList(1, 2);
        assertTrue(Closure.bindRecurse(listPat, listValue, (namedPat, o) -> {}));
        assertFalse(Closure.bindRecurse(listPat, Arrays.asList(1), (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_CONS_PAT() {
        Core.ConPat consPat = new Core.ConPat(Core.Op.CONS_PAT, new Core.TuplePat(Core.Op.TUPLE_PAT, Arrays.asList(
                new Core.LiteralPat(Core.Op.INT_LITERAL_PAT, new BigDecimal(1)),
                new Core.ListPat(Core.Op.LIST_PAT, Arrays.asList(
                        new Core.LiteralPat(Core.Op.INT_LITERAL_PAT, new BigDecimal(2))
                ))
        )), "cons");
        List<Object> consValue = Arrays.asList(1, Arrays.asList(2));
        assertTrue(Closure.bindRecurse(consPat, consValue, (namedPat, o) -> {}));
        assertFalse(Closure.bindRecurse(consPat, Arrays.asList(), (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_CON0_PAT() {
        Core.Con0Pat con0Pat = new Core.Con0Pat(Core.Op.CON0_PAT, "con0");
        List<Object> con0Value = Arrays.asList("con0");
        assertTrue(Closure.bindRecurse(con0Pat, con0Value, (namedPat, o) -> {}));
        assertFalse(Closure.bindRecurse(con0Pat, Arrays.asList("other"), (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_CON_PAT() {
        Core.ConPat conPat = new Core.ConPat(Core.Op.CON_PAT, new Core.LiteralPat(Core.Op.INT_LITERAL_PAT, new BigDecimal(1)), "con");
        List<Object> conValue = Arrays.asList("con", 1);
        assertTrue(Closure.bindRecurse(conPat, conValue, (namedPat, o) -> {}));
        assertFalse(Closure.bindRecurse(conPat, Arrays.asList("other", 1), (namedPat, o) -> {}));
    }

    @Test
    void testBindRecurse_DefaultCase() {
        Core.Pat unknownPat = new Core.Pat(Core.Op.UNKNOWN_OP) {};
        assertThrows(AssertionError.class, () -> Closure.bindRecurse(unknownPat, "value", (namedPat, o) -> {}));
    }
}
