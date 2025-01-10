
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
        RecordComponent recordComponent = mock(RecordComponent.class);
        when(recordComponent.getType()).thenReturn(Object.class);
        when(recordClass.getRecordComponents()).thenReturn(new RecordComponent[]{recordComponent});
        Constructor<?> recordConstructor = mock(Constructor.class);
        when(recordClass.getDeclaredConstructor(Class[].class)).thenReturn(recordConstructor);
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
        Constructor<?> constructor = mock(Constructor.class);
        when(nonRecordClass.getDeclaredConstructors()).thenReturn(new Constructor[]{constructor});
        when(constructor.newInstance()).thenReturn(new Object());

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
        Constructor<?> constructor = mock(Constructor.class);
        when(nonRecordClass.getDeclaredConstructors()).thenReturn(new Constructor[]{constructor});
        when(constructor.newInstance(any())).thenReturn(new Object());

        Object result = transformer.apply(nonRecordClass, schema);
        assertNotNull(result);
    }
}
