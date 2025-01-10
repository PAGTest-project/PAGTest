
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JsonTransformer_applyTest {

    @Test
    void testApplyWithSimpleFields() {
        // Given
        JsonTransformer<Object> transformer = JsonTransformer.builder().build();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ?>[] fields = new Field<?, ?>[2];
        fields[0] = new SimpleField("name", (Object o) -> "John");
        fields[1] = new SimpleField("age", (Object o) -> 30);
        when(schema.getFields()).thenReturn((Field<Object, ?>[]) fields);

        // When
        String result = transformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"name\": \"John\", \"age\": 30}", result);
    }

    @Test
    void testApplyWithCompositeField() {
        // Given
        JsonTransformer<Object> transformer = JsonTransformer.builder().build();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ?>[] fields = new Field<?, ?>[1];
        Field<?, ?>[] compositeFields = new Field<?, ?>[1];
        compositeFields[0] = new SimpleField("address", (Object o) -> "123 Main St");
        fields[0] = new CompositeField("contact", compositeFields);
        when(schema.getFields()).thenReturn((Field<Object, ?>[]) fields);

        // When
        String result = transformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"contact\": {\"address\": \"123 Main St\"}}", result);
    }
}
