
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.NOPClosure;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosureUtils_ifClosureTest {

    @Test
    public void testIfClosure_TruePredicate() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        Closure<String> trueClosure = input -> input.toUpperCase();
        Closure<String> falseClosure = NOPClosure.nopClosure();

        Closure<String> result = ClosureUtils.ifClosure(truePredicate, trueClosure, falseClosure);
        assertEquals("HELLO", result.execute("hello"));
    }

    @Test
    public void testIfClosure_FalsePredicate() {
        Predicate<String> falsePredicate = FalsePredicate.falsePredicate();
        Closure<String> trueClosure = input -> input.toUpperCase();
        Closure<String> falseClosure = input -> input.toLowerCase();

        Closure<String> result = ClosureUtils.ifClosure(falsePredicate, trueClosure, falseClosure);
        assertEquals("hello", result.execute("HELLO"));
    }
}
