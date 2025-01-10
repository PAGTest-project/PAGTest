
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class YamlTransformer_applyTest {

    @Test
    void testApplyWithEmptyFields() {
        YamlTransformer<Object> transformer = new YamlTransformer<>();
        Schema<Object, ?> schema = Schema.of();
        CharSequence result = transformer.apply(new Object(), schema);
        assertEquals("", result);
    }

    @Test
    void testApplyWithNonEmptyFields() {
        YamlTransformer<Object> transformer = new YamlTransformer<>();
        Field<Object, ?> field = new Field<>() {
            @Override
            public String getName() {
                return "testField";
            }

            @Override
            public Object transform(Object input) {
                return "testValue";
            }
        };
        Schema<Object, ?> schema = Schema.of(field);
        CharSequence result = transformer.apply(new Object(), schema);
        assertNotEquals("", result);
    }
}
