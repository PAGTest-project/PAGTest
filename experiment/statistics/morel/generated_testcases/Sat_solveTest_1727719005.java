
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
    public void testSolveWithSolution() {
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
    public void testSolveWithoutSolution() {
        final Variable x = sat.variable("x");
        final Variable y = sat.variable("y");

        // (x ∨ y) ∧ (¬x ∨ ¬y) ∧ (¬x ∨ y) ∧ (x ∨ ¬y)
        final Term clause0 = sat.or(x, y);
        final Term clause1 = sat.or(sat.not(x), sat.not(y));
        final Term clause2 = sat.or(sat.not(x), y);
        final Term clause3 = sat.or(x, sat.not(y));
        final Term formula = sat.and(clause0, clause1, clause2, clause3);

        final Map<Variable, Boolean> solution = sat.solve(formula);
        assertThat(solution, nullValue());
    }
}
