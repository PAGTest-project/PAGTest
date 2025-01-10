
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransformerUtils_switchTransformerTest {

    @Test
    public void testSwitchTransformerWithTruePredicate() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Predicate<Object> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<Object, String> trueTransformer = ConstantTransformer.constantTransformer("True");
        Transformer<Object, String> falseTransformer = ConstantTransformer.constantTransformer("False");

        Transformer<Object, String> transformer = TransformerUtils.switchTransformer(truePredicate, trueTransformer, falseTransformer);

        assertEquals("True", transformer.transform(new Object()));
    }

    @Test
    public void testSwitchTransformerWithFalsePredicate() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Predicate<Object> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<Object, String> trueTransformer = ConstantTransformer.constantTransformer("True");
        Transformer<Object, String> falseTransformer = ConstantTransformer.constantTransformer("False");

        Transformer<Object, String> transformer = TransformerUtils.switchTransformer(falsePredicate, trueTransformer, falseTransformer);

        assertEquals("False", transformer.transform(new Object()));
    }

    @Test
    public void testSwitchTransformerWithEqualPredicate() {
        Predicate<Object> equalPredicate = EqualPredicate.equalPredicate("Match");
        Transformer<Object, String> matchTransformer = ConstantTransformer.constantTransformer("Matched");
        Transformer<Object, String> defaultTransformer = ConstantTransformer.constantTransformer("Default");

        Transformer<Object, String> transformer = TransformerUtils.switchTransformer(equalPredicate, matchTransformer, defaultTransformer);

        assertEquals("Matched", transformer.transform("Match"));
        assertEquals("Default", transformer.transform("NoMatch"));
    }

    @Test
    public void testSwitchTransformerWithExceptionTransformer() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Transformer<Object, Object> exceptionTransformer = ExceptionTransformer.exceptionTransformer();
        Transformer<Object, Object> nopTransformer = NOPTransformer.nopTransformer();

        Transformer<Object, Object> transformer = TransformerUtils.switchTransformer(truePredicate, exceptionTransformer, nopTransformer);

        assertThrows(FunctorException.class, () -> transformer.transform(new Object()));
    }

    @Test
    public void testSwitchTransformerWithNullDefault() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Transformer<Object, Object> nopTransformer = NOPTransformer.nopTransformer();

        Transformer<Object, Object> transformer = TransformerUtils.switchTransformer(truePredicate, nopTransformer, null);

        assertNull(transformer.transform(new Object()));
    }
}
