
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Sat_solveTest {
    private Sat sat;

    @BeforeEach
    public void setUp() {
        sat = new Sat();
    }

    @Test
    public void testSolveWithValidSolution() {
        final Variable x = sat.variable("x");
        final Variable y = sat.variable("y");

        // (x ∨ y) ∧ (¬x ∨ y)
        final Term clause0 = sat.or(x, y);
        final Term clause1 = sat.or(sat.not(x), y);
        final Term formula = sat.and(clause0, clause1);

        final Map<Variable, Boolean> solution = sat.solve(formula);
        assertThat(solution, notNullValue());
        assertThat(solution, is(ImmutableMap.of(x, false, y, true)));
    }

    @Test
    public void testSolveWithNoSolution() {
        final Variable x = sat.variable("x");
        final Variable y = sat.variable("y");

        // (x) ∧ (¬x)
        final Term clause0 = x;
        final Term clause1 = sat.not(x);
        final Term formula = sat.and(clause0, clause1);

        final Map<Variable, Boolean> solution = sat.solve(formula);
        assertThat(solution, nullValue());
    }

    @Test
    public void testSolveWithSingleVariable() {
        final Variable x = sat.variable("x");

        // (x)
        final Term formula = x;

        final Map<Variable, Boolean> solution = sat.solve(formula);
        assertThat(solution, notNullValue());
        assertThat(solution, is(ImmutableMap.of(x, true)));
    }

    @Test
    public void testSolveWithComplexFormula() {
        final Variable x = sat.variable("x");
        final Variable y = sat.variable("y");
        final Variable z = sat.variable("z");

        // (x ∨ y) ∧ (¬x ∨ z) ∧ (¬y ∨ ¬z)
        final Term clause0 = sat.or(x, y);
        final Term clause1 = sat.or(sat.not(x), z);
        final Term clause2 = sat.or(sat.not(y), sat.not(z));
        final Term formula = sat.and(clause0, clause1, clause2);

        final Map<Variable, Boolean> solution = sat.solve(formula);
        assertThat(solution, notNullValue());
        assertThat(solution, is(ImmutableMap.of(x, true, y, false, z, false)));
    }
}
