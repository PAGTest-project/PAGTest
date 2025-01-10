
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CsvTransformer_applyTest {

    @Test
    public void testApply_SingleField() {
        // Given
        CsvTransformer<String> transformer = new CsvTransformer<>(";", '"', false);
        Schema<String, ?> schema = mock(Schema.class);
        Field<String, ?>[] fields = new Field[1];
        SimpleField<Object, ?> field = mock(SimpleField.class);
        when(field.transform(any())).thenReturn("value");
        fields[0] = field;
        when(schema.getFields()).thenReturn(fields);

        // When
        CharSequence result = transformer.apply("input", schema);

        // Then
        assertEquals("value", result.toString());
    }

    @Test
    public void testApply_MultipleFields() {
        // Given
        CsvTransformer<String> transformer = new CsvTransformer<>(";", '"', false);
        Schema<String, ?> schema = mock(Schema.class);
        Field<String, ?>[] fields = new Field[2];
        SimpleField<Object, ?> field1 = mock(SimpleField.class);
        SimpleField<Object, ?> field2 = mock(SimpleField.class);
        when(field1.transform(any())).thenReturn("value1");
        when(field2.transform(any())).thenReturn("value2");
        fields[0] = field1;
        fields[1] = field2;
        when(schema.getFields()).thenReturn(fields);

        // When
        CharSequence result = transformer.apply("input", schema);

        // Then
        assertEquals("value1;value2", result.toString());
    }
}
