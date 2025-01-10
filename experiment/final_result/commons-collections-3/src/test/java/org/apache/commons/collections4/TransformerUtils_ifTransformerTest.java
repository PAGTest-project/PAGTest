
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransformerUtils_ifTransformerTest {

    @Test
    public void testIfTransformer() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        Predicate<String> falsePredicate = FalsePredicate.falsePredicate();
        Transformer<String, Integer> trueTransformer = ConstantTransformer.constantTransformer(1);
        Transformer<String, Integer> falseTransformer = ConstantTransformer.constantTransformer(0);

        Transformer<String, Integer> transformer = TransformerUtils.ifTransformer(truePredicate, trueTransformer, falseTransformer);

        assertEquals(1, transformer.transform("anyInput"));

        transformer = TransformerUtils.ifTransformer(falsePredicate, trueTransformer, falseTransformer);

        assertEquals(0, transformer.transform("anyInput"));
    }
}
