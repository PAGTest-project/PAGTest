
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransformerUtils_switchMapTransformerTest {

    @Test
    public void testSwitchMapTransformer_WithDefaultTransformer() {
        // Given
        Map<Integer, Transformer<Integer, String>> map = new HashMap<>();
        map.put(1, new ConstantTransformer<>("One"));
        map.put(2, new ConstantTransformer<>("Two"));
        map.put(null, new ConstantTransformer<>("Default"));

        // When
        Transformer<Integer, String> transformer = TransformerUtils.switchMapTransformer(map);

        // Then
        assertEquals("One", transformer.transform(1));
        assertEquals("Two", transformer.transform(2));
        assertEquals("Default", transformer.transform(3));
    }

    @Test
    public void testSwitchMapTransformer_WithoutDefaultTransformer() {
        // Given
        Map<Integer, Transformer<Integer, String>> map = new HashMap<>();
        map.put(1, new ConstantTransformer<>("One"));
        map.put(2, new ConstantTransformer<>("Two"));

        // When
        Transformer<Integer, String> transformer = TransformerUtils.switchMapTransformer(map);

        // Then
        assertEquals("One", transformer.transform(1));
        assertEquals("Two", transformer.transform(2));
        assertEquals(null, transformer.transform(3));
    }

    @Test
    public void testSwitchMapTransformer_NullMap() {
        // Given
        Map<Integer, Transformer<Integer, String>> map = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            TransformerUtils.switchMapTransformer(map);
        });
    }
}
