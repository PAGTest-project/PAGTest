
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_transformedIteratorTest {

    @Test
    public void testTransformedIterator_Success() {
        Iterator<String> inputIterator = Arrays.asList("1", "2", "3").iterator();
        Transformer<String, Integer> transformer = new ConstantTransformer<>(1);

        Iterator<Integer> result = IteratorUtils.transformedIterator(inputIterator, transformer);

        assertNotNull(result);
    }

    @Test
    public void testTransformedIterator_NullIterator() {
        Transformer<String, Integer> transformer = new ConstantTransformer<>(1);

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.transformedIterator(null, transformer);
        });
    }

    @Test
    public void testTransformedIterator_NullTransformer() {
        Iterator<String> inputIterator = Arrays.asList("1", "2", "3").iterator();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.transformedIterator(inputIterator, null);
        });
    }
}
