
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformerUtils_switchTransformerTest {

    @Test
    public void testSwitchTransformer() {
        Predicate<String> predicate = EqualPredicate.equalPredicate("true");
        Transformer<String, Integer> trueTransformer = ConstantTransformer.constantTransformer(1);
        Transformer<String, Integer> falseTransformer = ConstantTransformer.constantTransformer(0);

        Transformer<String, Integer> transformer = TransformerUtils.switchTransformer(predicate, trueTransformer, falseTransformer);

        assertEquals(1, transformer.transform("true"));
        assertEquals(0, transformer.transform("false"));
    }
}
