
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CsvTransformer_applyTest {

    @Test
    public void testApply_SingleField() {
        // Given
        CsvTransformer<String> transformer = CsvTransformer.<String>builder().build();
        Schema<String, ?> schema = mock(Schema.class);
        SimpleField<String, ?> field = mock(SimpleField.class);
        when(schema.getFields()).thenReturn(new Field[]{field});
        when(field.transform(any())).thenReturn("value");

        // When
        CharSequence result = transformer.apply("input", schema);

        // Then
        assertEquals("value", result.toString());
    }

    @Test
    public void testApply_MultipleFields() {
        // Given
        CsvTransformer<String> transformer = CsvTransformer.<String>builder().build();
        Schema<String, ?> schema = mock(Schema.class);
        SimpleField<String, ?> field1 = mock(SimpleField.class);
        SimpleField<String, ?> field2 = mock(SimpleField.class);
        when(schema.getFields()).thenReturn(new Field[]{field1, field2});
        when(field1.transform(any())).thenReturn("value1");
        when(field2.transform(any())).thenReturn("value2");

        // When
        CharSequence result = transformer.apply("input", schema);

        // Then
        assertEquals("value1;value2", result.toString());
    }
}
