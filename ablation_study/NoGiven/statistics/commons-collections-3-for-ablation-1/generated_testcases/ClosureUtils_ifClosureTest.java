
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.NOPClosure;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosureUtils_ifClosureTest {

    @Test
    public void testIfClosure_TruePredicate() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Closure<Object> trueClosure = NOPClosure.nopClosure();
        Closure<Object> falseClosure = NOPClosure.nopClosure();

        Closure<Object> result = ClosureUtils.ifClosure(truePredicate, trueClosure, falseClosure);

        assertEquals(IfClosure.class, result.getClass());
    }

    @Test
    public void testIfClosure_FalsePredicate() {
        Predicate<Object> falsePredicate = FalsePredicate.falsePredicate();
        Closure<Object> trueClosure = NOPClosure.nopClosure();
        Closure<Object> falseClosure = NOPClosure.nopClosure();

        Closure<Object> result = ClosureUtils.ifClosure(falsePredicate, trueClosure, falseClosure);

        assertEquals(IfClosure.class, result.getClass());
    }
}
