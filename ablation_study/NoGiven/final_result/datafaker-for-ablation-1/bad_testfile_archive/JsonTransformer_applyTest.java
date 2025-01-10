
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JsonTransformer_applyTest {

    @Test
    public void testApply_SingleSimpleField() {
        // Given
        JsonTransformer<Object> transformer = new JsonTransformer<>(true);
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ?> field = mock(SimpleField.class);
        when(schema.getFields()).thenReturn(new Field[]{field});
        when(field.getName()).thenReturn("name");
        when(field.transform(any())).thenReturn("value");

        // When
        String result = transformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"name\": \"value\"}", result);
    }

    @Test
    public void testApply_SingleCompositeField() {
        // Given
        JsonTransformer<Object> transformer = new JsonTransformer<>(true);
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ?> field = mock(CompositeField.class);
        when(schema.getFields()).thenReturn(new Field[]{field});
        when(field.getName()).thenReturn("composite");
        when(field.transform(any())).thenReturn(new Object());
        when(field.apply(any(), any(), anyInt())).thenReturn("{\"nested\": \"value\"}");

        // When
        String result = transformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"composite\": {\"nested\": \"value\"}}", result);
    }

    @Test
    public void testApply_MultipleFields() {
        // Given
        JsonTransformer<Object> transformer = new JsonTransformer<>(true);
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ?> field1 = mock(SimpleField.class);
        Field<?, ?> field2 = mock(CompositeField.class);
        when(schema.getFields()).thenReturn(new Field[]{field1, field2});
        when(field1.getName()).thenReturn("name1");
        when(field1.transform(any())).thenReturn("value1");
        when(field2.getName()).thenReturn("name2");
        when(field2.transform(any())).thenReturn(new Object());
        when(field2.apply(any(), any(), anyInt())).thenReturn("{\"nested\": \"value2\"}");

        // When
        String result = transformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"name1\": \"value1\", \"name2\": {\"nested\": \"value2\"}}", result);
    }
}
