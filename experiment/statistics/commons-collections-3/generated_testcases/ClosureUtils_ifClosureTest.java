
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.NOPClosure;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.functors.IfClosure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ClosureUtils_ifClosureTest {

    @Test
    public void testIfClosure() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Closure<Object> trueClosure = NOPClosure.nopClosure();
        Closure<Object> falseClosure = NOPClosure.nopClosure();

        Closure<Object> result = ClosureUtils.ifClosure(truePredicate, trueClosure, falseClosure);

        assertInstanceOf(IfClosure.class, result);
    }
}
