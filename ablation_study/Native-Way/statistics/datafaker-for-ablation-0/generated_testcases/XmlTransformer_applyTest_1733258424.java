
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class XmlTransformer_applyTest {

    @Test
    public void testApply() {
        // Given
        XmlTransformer<String> transformer = new XmlTransformer<>(false);
        Schema<String, ?> schema = mock(Schema.class);
        Field<String, ?> field1 = mock(Field.class);
        Field<String, ?> field2 = mock(Field.class);
        when(schema.getFields()).thenReturn(new Field[]{field1, field2});

        // When
        CharSequence result = transformer.apply("input", schema);

        // Then
        assertEquals("", result.toString());
        verify(schema, times(1)).getFields();
        verify(field1, times(1)).getName();
        verify(field2, times(1)).getName();
    }
}
