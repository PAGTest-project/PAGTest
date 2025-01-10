
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.apache.commons.collections4.functors.StringValueTransformer;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransformerUtils_switchTransformerTest {

    @Test
    public void testSwitchTransformerWithTruePredicate() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        Transformer<String, Integer> trueTransformer = ConstantTransformer.constantTransformer(1);
        Transformer<String, Integer> falseTransformer = ConstantTransformer.constantTransformer(0);

        Transformer<String, Integer> transformer = TransformerUtils.switchTransformer(truePredicate, trueTransformer, falseTransformer);

        assertEquals(1, transformer.transform("anyInput"));
    }

    @Test
    public void testSwitchTransformerWithFalsePredicate() {
        Predicate<String> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<String, Integer> trueTransformer = ConstantTransformer.constantTransformer(1);
        Transformer<String, Integer> falseTransformer = ConstantTransformer.constantTransformer(0);

        Transformer<String, Integer> transformer = TransformerUtils.switchTransformer(falsePredicate, trueTransformer, falseTransformer);

        assertEquals(0, transformer.transform("anyInput"));
    }

    @Test
    public void testSwitchTransformerWithEqualPredicate() {
        Predicate<String> equalPredicate = EqualPredicate.equalPredicate("match");
        Transformer<String, Integer> trueTransformer = ConstantTransformer.constantTransformer(1);
        Transformer<String, Integer> falseTransformer = ConstantTransformer.constantTransformer(0);

        Transformer<String, Integer> transformer = TransformerUtils.switchTransformer(equalPredicate, trueTransformer, falseTransformer);

        assertEquals(1, transformer.transform("match"));
        assertEquals(0, transformer.transform("noMatch"));
    }

    @Test
    public void testSwitchTransformerWithExceptionTransformer() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        Transformer<String, Integer> trueTransformer = ExceptionTransformer.exceptionTransformer();
        Transformer<String, Integer> falseTransformer = ConstantTransformer.constantTransformer(0);

        Transformer<String, Integer> transformer = TransformerUtils.switchTransformer(truePredicate, trueTransformer, falseTransformer);

        assertThrows(FunctorException.class, () -> transformer.transform("anyInput"));
    }

    @Test
    public void testSwitchTransformerWithNullDefaultTransformer() {
        Predicate<String> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<String, Integer> trueTransformer = ConstantTransformer.constantTransformer(1);
        Transformer<String, Integer> falseTransformer = null;

        Transformer<String, Integer> transformer = TransformerUtils.switchTransformer(falsePredicate, trueTransformer, falseTransformer);

        assertNull(transformer.transform("anyInput"));
    }

    @Test
    public void testSwitchTransformerWithNOPTransformer() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        Transformer<String, String> trueTransformer = NOPTransformer.nopTransformer();
        Transformer<String, String> falseTransformer = StringValueTransformer.stringValueTransformer();

        Transformer<String, String> transformer = TransformerUtils.switchTransformer(truePredicate, trueTransformer, falseTransformer);

        assertEquals("anyInput", transformer.transform("anyInput"));
    }
}
