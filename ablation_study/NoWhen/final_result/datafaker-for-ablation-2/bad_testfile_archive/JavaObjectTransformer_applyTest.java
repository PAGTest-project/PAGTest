
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class JavaObjectTransformer_applyTest {

    @Test
    void testApplyWithRecordClass() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Given
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<Object, ?>[] fields = new Field[]{mock(Field.class)};
        when(schema.getFields()).thenReturn(fields);

        Class<?> recordClass = mock(Class.class);
        when(recordClass.isRecord()).thenReturn(true);
        RecordComponent recordComponent = mock(RecordComponent.class);
        when(recordComponent.getType()).thenReturn(Object.class);
        when(recordClass.getRecordComponents()).thenReturn(new RecordComponent[]{recordComponent});
        Constructor<?> recordConstructor = mock(Constructor.class);
        when(recordClass.getDeclaredConstructor(Class[].class)).thenReturn(recordConstructor);
        when(recordConstructor.newInstance(any())).thenReturn(new Object());

        // When
        Object result = transformer.apply(recordClass, schema);

        // Then
        verify(recordConstructor).newInstance(any());
        assertEquals(new Object(), result);
    }

    @Test
    void testApplyWithNonRecordClassAndParameterlessConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Given
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<Object, ?>[] fields = new Field[]{mock(Field.class)};
        when(schema.getFields()).thenReturn(fields);

        Class<?> nonRecordClass = mock(Class.class);
        when(nonRecordClass.isRecord()).thenReturn(false);
        Constructor<?> primaryConstructor = mock(Constructor.class);
        when(nonRecordClass.getDeclaredConstructors()).thenReturn(new Constructor[]{primaryConstructor});
        when(primaryConstructor.newInstance()).thenReturn(new Object());

        // When
        Object result = transformer.apply(nonRecordClass, schema);

        // Then
        verify(primaryConstructor).newInstance();
        assertEquals(new Object(), result);
    }

    @Test
    void testApplyWithNonRecordClassAndNoParameterlessConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Given
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<Object, ?>[] fields = new Field[]{mock(Field.class)};
        when(schema.getFields()).thenReturn(fields);

        Class<?> nonRecordClass = mock(Class.class);
        when(nonRecordClass.isRecord()).thenReturn(false);
        Constructor<?> primaryConstructor = mock(Constructor.class);
        when(nonRecordClass.getDeclaredConstructors()).thenReturn(new Constructor[]{primaryConstructor});
        when(primaryConstructor.newInstance(any())).thenReturn(new Object());

        // When
        Object result = transformer.apply(nonRecordClass, schema);

        // Then
        verify(primaryConstructor).newInstance(any());
        assertEquals(new Object(), result);
    }

    @Test
    void testApplyWithException() {
        // Given
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<Object, ?>[] fields = new Field[]{mock(Field.class)};
        when(schema.getFields()).thenReturn(fields);

        Class<?> recordClass = mock(Class.class);
        when(recordClass.isRecord()).thenReturn(true);
        when(recordClass.getRecordComponents()).thenThrow(new RuntimeException("Test Exception"));

        // When & Then
        assertThrows(RuntimeException.class, () -> transformer.apply(recordClass, schema));
    }
}
