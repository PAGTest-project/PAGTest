
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformerUtils_switchTransformerTest {

    @Test
    public void testSwitchTransformer() {
        // Given
        Predicate<Integer> predicate = EqualPredicate.equalPredicate(1);
        Transformer<Integer, String> trueTransformer = ConstantTransformer.constantTransformer("True");
        Transformer<Integer, String> falseTransformer = ConstantTransformer.constantTransformer("False");

        // When
        Transformer<Integer, String> transformer = TransformerUtils.switchTransformer(predicate, trueTransformer, falseTransformer);

        // Then
        assertEquals("True", transformer.transform(1));
        assertEquals("False", transformer.transform(2));
    }
}
