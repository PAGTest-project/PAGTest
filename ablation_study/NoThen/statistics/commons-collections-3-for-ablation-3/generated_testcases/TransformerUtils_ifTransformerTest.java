
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransformerUtils_ifTransformerTest {

    private static final Object CONSTANT_VALUE = new Object();

    @Test
    public void testIfTransformerWithTruePredicate() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Transformer<Object, Object> trueTransformer = ConstantTransformer.constantTransformer(CONSTANT_VALUE);
        Transformer<Object, Object> falseTransformer = ConstantTransformer.constantTransformer(null);

        Transformer<Object, Object> ifTransformer = TransformerUtils.ifTransformer(truePredicate, trueTransformer, falseTransformer);

        assertSame(CONSTANT_VALUE, ifTransformer.transform(new Object()));
    }

    @Test
    public void testIfTransformerWithFalsePredicate() {
        Predicate<Object> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<Object, Object> trueTransformer = ConstantTransformer.constantTransformer(CONSTANT_VALUE);
        Transformer<Object, Object> falseTransformer = ConstantTransformer.constantTransformer(null);

        Transformer<Object, Object> ifTransformer = TransformerUtils.ifTransformer(falsePredicate, trueTransformer, falseTransformer);

        assertNull(ifTransformer.transform(new Object()));
    }

    @Test
    public void testIfTransformerWithEqualPredicate() {
        Object input = new Object();
        Predicate<Object> equalPredicate = EqualPredicate.equalPredicate(input);
        Transformer<Object, Object> trueTransformer = ConstantTransformer.constantTransformer(CONSTANT_VALUE);
        Transformer<Object, Object> falseTransformer = ConstantTransformer.constantTransformer(null);

        Transformer<Object, Object> ifTransformer = TransformerUtils.ifTransformer(equalPredicate, trueTransformer, falseTransformer);

        assertSame(CONSTANT_VALUE, ifTransformer.transform(input));
        assertNull(ifTransformer.transform(new Object()));
    }

    @Test
    public void testIfTransformerWithExceptionTransformer() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Transformer<Object, Object> trueTransformer = ExceptionTransformer.exceptionTransformer();
        Transformer<Object, Object> falseTransformer = ConstantTransformer.constantTransformer(null);

        Transformer<Object, Object> ifTransformer = TransformerUtils.ifTransformer(truePredicate, trueTransformer, falseTransformer);

        assertThrows(FunctorException.class, () -> ifTransformer.transform(new Object()));
    }
}
