
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.NOPClosure;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ClosureUtils_ifClosureTest {

    @Test
    public void testIfClosure() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Closure<Object> trueClosure = NOPClosure.nopClosure();
        Closure<Object> falseClosure = NOPClosure.nopClosure();

        Closure<Object> result = ClosureUtils.ifClosure(truePredicate, trueClosure, falseClosure);

        assertSame(trueClosure, result);
    }
}
