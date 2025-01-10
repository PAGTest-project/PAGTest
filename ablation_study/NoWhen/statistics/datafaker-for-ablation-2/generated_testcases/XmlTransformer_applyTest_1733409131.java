
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
        Field<String, ?> field = mock(Field.class);
        when(schema.getFields()).thenReturn(new Field[]{field});
        when(field.getName()).thenReturn("tag");
        when(field.transform(any())).thenReturn("value");

        // When
        CharSequence result = transformer.apply("input", schema);

        // Then
        assertEquals("<tag>value</tag>", result.toString());
    }
}
