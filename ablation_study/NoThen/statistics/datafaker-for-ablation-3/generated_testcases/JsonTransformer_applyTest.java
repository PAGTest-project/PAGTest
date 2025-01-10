
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JsonTransformer_applyTest {

    @Test
    public void testApply_SimpleField() {
        // Given
        JsonTransformer<Object> transformer = JsonTransformer.builder().build();
        Schema<Object, ?> schema = mock(Schema.class);
        SimpleField simpleField = mock(SimpleField.class);
        Field<?, ?>[] fields = new Field[]{simpleField};
        when(schema.getFields()).thenReturn(fields);
        when(simpleField.getName()).thenReturn("name");
        when(simpleField.transform(any())).thenReturn("value");

        // When
        String result = transformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"name\": \"value\"}", result);
    }

    @Test
    public void testApply_CompositeField() {
        // Given
        JsonTransformer<Object> transformer = JsonTransformer.builder().build();
        Schema<Object, ?> schema = mock(Schema.class);
        CompositeField compositeField = mock(CompositeField.class);
        Field<?, ?>[] fields = new Field[]{compositeField};
        when(schema.getFields()).thenReturn(fields);
        when(compositeField.getName()).thenReturn("composite");
        when(compositeField.transform(any())).thenReturn(new CompositeField("subField", new Field[]{}));

        // When
        String result = transformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"composite\": {}}", result);
    }
}
