
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransformerUtils_switchTransformerTest {

    @Test
    public void testSwitchTransformerWithTruePredicate() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        Transformer<String, String> trueTransformer = ConstantTransformer.constantTransformer("True");
        Transformer<String, String> falseTransformer = ConstantTransformer.constantTransformer("False");

        Transformer<String, String> transformer = TransformerUtils.switchTransformer(truePredicate, trueTransformer, falseTransformer);

        assertEquals("True", transformer.transform("Input"));
    }

    @Test
    public void testSwitchTransformerWithFalsePredicate() {
        Predicate<String> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<String, String> trueTransformer = ConstantTransformer.constantTransformer("True");
        Transformer<String, String> falseTransformer = ConstantTransformer.constantTransformer("False");

        Transformer<String, String> transformer = TransformerUtils.switchTransformer(falsePredicate, trueTransformer, falseTransformer);

        assertEquals("False", transformer.transform("Input"));
    }

    @Test
    public void testSwitchTransformerWithEqualPredicate() {
        Predicate<String> equalPredicate = EqualPredicate.equalPredicate("Match");
        Transformer<String, String> trueTransformer = ConstantTransformer.constantTransformer("Matched");
        Transformer<String, String> falseTransformer = ConstantTransformer.constantTransformer("NotMatched");

        Transformer<String, String> transformer = TransformerUtils.switchTransformer(equalPredicate, trueTransformer, falseTransformer);

        assertEquals("Matched", transformer.transform("Match"));
        assertEquals("NotMatched", transformer.transform("NoMatch"));
    }

    @Test
    public void testSwitchTransformerWithExceptionTransformer() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        Transformer<String, String> trueTransformer = ExceptionTransformer.exceptionTransformer();
        Transformer<String, String> falseTransformer = NOPTransformer.nopTransformer();

        Transformer<String, String> transformer = TransformerUtils.switchTransformer(truePredicate, trueTransformer, falseTransformer);

        assertThrows(FunctorException.class, () -> transformer.transform("Input"));
    }
}
