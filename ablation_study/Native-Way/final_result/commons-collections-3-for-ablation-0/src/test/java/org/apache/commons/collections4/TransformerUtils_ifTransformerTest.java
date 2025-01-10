
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransformerUtils_ifTransformerTest {

    private static final Object cObject = new Object();
    private static final Object cString = "Test";
    private static final Object cInteger = 1;

    @Test
    public void testIfTransformer() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Predicate<Object> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<Object, Object> trueTransformer = ConstantTransformer.constantTransformer(cString);
        Transformer<Object, Object> falseTransformer = ConstantTransformer.constantTransformer(cInteger);

        Transformer<Object, Object> ifTransformerTrue = TransformerUtils.ifTransformer(truePredicate, trueTransformer, falseTransformer);
        Transformer<Object, Object> ifTransformerFalse = TransformerUtils.ifTransformer(falsePredicate, trueTransformer, falseTransformer);

        assertEquals(cString, ifTransformerTrue.transform(cObject));
        assertEquals(cInteger, ifTransformerFalse.transform(cObject));
    }

    @Test
    public void testIfTransformerWithNullPredicate() {
        Transformer<Object, Object> trueTransformer = ConstantTransformer.constantTransformer(cString);
        Transformer<Object, Object> falseTransformer = ConstantTransformer.constantTransformer(cInteger);

        assertThrows(NullPointerException.class, () -> {
            TransformerUtils.ifTransformer(null, trueTransformer, falseTransformer);
        });
    }

    @Test
    public void testIfTransformerWithNullTrueTransformer() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Transformer<Object, Object> falseTransformer = ConstantTransformer.constantTransformer(cInteger);

        assertThrows(NullPointerException.class, () -> {
            TransformerUtils.ifTransformer(truePredicate, null, falseTransformer);
        });
    }

    @Test
    public void testIfTransformerWithNullFalseTransformer() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Transformer<Object, Object> trueTransformer = ConstantTransformer.constantTransformer(cString);

        assertThrows(NullPointerException.class, () -> {
            TransformerUtils.ifTransformer(truePredicate, trueTransformer, null);
        });
    }

    @Test
    public void testIfTransformerWithExceptionTransformer() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Predicate<Object> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<Object, Object> exceptionTransformer = ExceptionTransformer.exceptionTransformer();
        Transformer<Object, Object> nopTransformer = NOPTransformer.nopTransformer();

        Transformer<Object, Object> ifTransformerTrue = TransformerUtils.ifTransformer(truePredicate, exceptionTransformer, nopTransformer);
        Transformer<Object, Object> ifTransformerFalse = TransformerUtils.ifTransformer(falsePredicate, exceptionTransformer, nopTransformer);

        assertThrows(FunctorException.class, () -> {
            ifTransformerTrue.transform(cObject);
        });

        assertEquals(cObject, ifTransformerFalse.transform(cObject));
    }
}
