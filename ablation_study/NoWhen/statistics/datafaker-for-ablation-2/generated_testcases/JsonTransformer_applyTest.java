
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonTransformer_applyTest {

    @Test
    void testApplyWithSimpleField() {
        // Given
        SimpleField simpleField = mock(SimpleField.class);
        when(simpleField.getName()).thenReturn("name");
        when(simpleField.transform(any())).thenReturn("value");

        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field[]{simpleField});

        JsonTransformer<Object> jsonTransformer = new JsonTransformer<>();

        // When
        String result = jsonTransformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"name\": \"value\"}", result);
    }

    @Test
    void testApplyWithCompositeField() {
        // Given
        CompositeField compositeField = mock(CompositeField.class);
        when(compositeField.getName()).thenReturn("composite");

        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field[]{compositeField});

        JsonTransformer<Object> jsonTransformer = new JsonTransformer<>();

        // When
        String result = jsonTransformer.apply(new Object(), schema);

        // Then
        assertEquals("{\"composite\": {}}", result);
    }
}
