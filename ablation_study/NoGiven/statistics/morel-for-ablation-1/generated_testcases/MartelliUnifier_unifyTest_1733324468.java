
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableMap;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MartelliUnifier_unifyTest {

    @Test
    void testUnify_Delete() {
        MartelliUnifier unifier = new MartelliUnifier();
        List<TermTerm> termPairs = new ArrayList<>();
        termPairs.add(new TermTerm(new Variable("x"), new Variable("x")));
        Map<Variable, Action> termActions = new HashMap<>();
        Tracer tracer = new Tracer();

        Result result = unifier.unify(termPairs, termActions, tracer);

        assertTrue(termPairs.isEmpty());
        assertTrue(result instanceof SubstitutionResult);
        assertEquals(0, ((SubstitutionResult) result).getResult().size());
    }

    @Test
    void testUnify_Conflict() {
        MartelliUnifier unifier = new MartelliUnifier();
        List<TermTerm> termPairs = new ArrayList<>();
        termPairs.add(new TermTerm(new Sequence("f", List.of(new Variable("x"))), new Sequence("g", List.of(new Variable("y")))));
        Map<Variable, Action> termActions = new HashMap<>();
        Tracer tracer = new Tracer();

        Result result = unifier.unify(termPairs, termActions, tracer);

        assertTrue(result instanceof FailureResult);
        assertEquals("conflict: f(x) vs g(y)", ((FailureResult) result).getMessage());
    }

    @Test
    void testUnify_Swap() {
        MartelliUnifier unifier = new MartelliUnifier();
        List<TermTerm> termPairs = new ArrayList<>();
        termPairs.add(new TermTerm(new Sequence("f", List.of(new Variable("x"))), new Variable("y")));
        Map<Variable, Action> termActions = new HashMap<>();
        Tracer tracer = new Tracer();

        Result result = unifier.unify(termPairs, termActions, tracer);

        assertEquals(1, termPairs.size());
        assertEquals(new TermTerm(new Variable("y"), new Sequence("f", List.of(new Variable("x")))), termPairs.get(0));
    }

    @Test
    void testUnify_Cycle() {
        MartelliUnifier unifier = new MartelliUnifier();
        List<TermTerm> termPairs = new ArrayList<>();
        Variable x = new Variable("x");
        termPairs.add(new TermTerm(x, new Sequence("f", List.of(x))));
        Map<Variable, Action> termActions = new HashMap<>();
        Tracer tracer = new Tracer();

        Result result = unifier.unify(termPairs, termActions, tracer);

        assertTrue(result instanceof FailureResult);
        assertEquals("cycle: variable x in f(x)", ((FailureResult) result).getMessage());
    }

    @Test
    void testUnify_Success() {
        MartelliUnifier unifier = new MartelliUnifier();
        List<TermTerm> termPairs = new ArrayList<>();
        termPairs.add(new TermTerm(new Variable("x"), new Sequence("f", List.of(new Variable("y")))));
        Map<Variable, Action> termActions = new HashMap<>();
        Tracer tracer = new Tracer();

        Result result = unifier.unify(termPairs, termActions, tracer);

        assertTrue(result instanceof SubstitutionResult);
        assertEquals(1, ((SubstitutionResult) result).getResult().size());
        assertEquals(new Sequence("f", List.of(new Variable("y"))), ((SubstitutionResult) result).getResult().get(new Variable("x")));
    }

    // Mock classes for testing
    private static class TermTerm {
        Term left;
        Term right;

        TermTerm(Term left, Term right) {
            this.left = left;
            this.right = right;
        }
    }

    private static class Term {
        String name;

        Term(String name) {
            this.name = name;
        }

        boolean contains(Variable variable) {
            return this.name.equals(variable.name);
        }

        Term apply(Map<Variable, Term> map) {
            return this;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Term) {
                return this.name.equals(((Term) obj).name);
            }
            return false;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class Variable extends Term {
        Variable(String name) {
            super(name);
        }
    }

    private static class Sequence extends Term {
        String operator;
        List<Term> terms;

        Sequence(String operator, List<Term> terms) {
            super(operator);
            this.operator = operator;
            this.terms = terms;
        }

        @Override
        public String toString() {
            return operator + "(" + terms.toString().replace("[", "").replace("]", "") + ")";
        }
    }

    private static class Tracer {
        void onDelete(Term left, Term right) {}
        void onConflict(Sequence left, Sequence right) {}
        void onSwap(Term left, Term right) {}
        void onCycle(Variable variable, Term term) {}
        void onVariable(Variable variable, Term term) {}
        void onSequence(Sequence left, Sequence right) {}
        void onSubstitute(Term left, Term right, Term left2, Term right2) {}
    }

    private static class Result {}

    private static class SubstitutionResult extends Result {
        Map<Variable, Term> result;

        static SubstitutionResult create(Map<Variable, Term> result) {
            SubstitutionResult sr = new SubstitutionResult();
            sr.result = result;
            return sr;
        }

        Map<Variable, Term> getResult() {
            return result;
        }
    }

    private static class FailureResult extends Result {
        String message;

        FailureResult(String message) {
            this.message = message;
        }

        String getMessage() {
            return message;
        }
    }

    private static Result failure(String message) {
        return new FailureResult(message);
    }

    private static class Action {
        void accept(Variable variable, Term term, Substitution substitution, List<TermTerm> termPairs) {}
    }

    private static class Substitution {
        Map<Variable, Term> resultMap;

        Substitution(Map<Variable, Term> resultMap) {
            this.resultMap = resultMap;
        }
    }
}
