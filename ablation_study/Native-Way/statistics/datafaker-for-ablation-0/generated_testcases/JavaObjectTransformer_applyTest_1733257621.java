
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JavaObjectTransformer_applyTest {

    @Test
    void testApplyWithRecordClass() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<Object, ?>[] fields = new Field[]{mock(Field.class)};
        when(schema.getFields()).thenReturn(fields);

        Class<?> recordClass = mock(Class.class);
        when(recordClass.isRecord()).thenReturn(true);
        RecordComponent[] recordComponents = new RecordComponent[]{mock(RecordComponent.class)};
        when(recordClass.getRecordComponents()).thenReturn(recordComponents);
        when(recordComponents[0].getType()).thenReturn(Object.class);

        Constructor<?> recordConstructor = mock(Constructor.class);
        when(recordClass.getDeclaredConstructor(Object.class)).thenReturn(recordConstructor);
        when(recordConstructor.newInstance(any())).thenReturn(new Object());

        Object result = transformer.apply(recordClass, schema);
        assertNotNull(result);
    }

    @Test
    void testApplyWithNonRecordClassAndParameterlessConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<Object, ?>[] fields = new Field[]{mock(Field.class)};
        when(schema.getFields()).thenReturn(fields);

        Class<?> nonRecordClass = mock(Class.class);
        when(nonRecordClass.isRecord()).thenReturn(false);
        when(nonRecordClass.getDeclaredFields()).thenReturn(new java.lang.reflect.Field[0]);
        Constructor<?> primaryConstructor = mock(Constructor.class);
        when(nonRecordClass.getDeclaredConstructors()).thenReturn(new Constructor[]{primaryConstructor});
        when(primaryConstructor.newInstance()).thenReturn(new Object());

        Object result = transformer.apply(nonRecordClass, schema);
        assertNotNull(result);
    }

    @Test
    void testApplyWithNonRecordClassAndNoParameterlessConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<Object, ?>[] fields = new Field[]{mock(Field.class)};
        when(schema.getFields()).thenReturn(fields);

        Class<?> nonRecordClass = mock(Class.class);
        when(nonRecordClass.isRecord()).thenReturn(false);
        when(nonRecordClass.getDeclaredFields()).thenReturn(new java.lang.reflect.Field[0]);
        Constructor<?> primaryConstructor = mock(Constructor.class);
        when(nonRecordClass.getDeclaredConstructors()).thenReturn(new Constructor[]{primaryConstructor});
        when(primaryConstructor.newInstance(any())).thenReturn(new Object());

        Object result = transformer.apply(nonRecordClass, schema);
        assertNotNull(result);
    }
}
