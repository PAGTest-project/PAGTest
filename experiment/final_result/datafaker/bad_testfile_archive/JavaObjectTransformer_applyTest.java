
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.IdentityHashMap;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class JavaObjectTransformer_applyTest {

    private static final Map<Schema<Object, ?>, Consumer<Object>> SCHEMA2CONSUMER = new IdentityHashMap<>();
    private static final Map<Class<?>, Constructor<?>> CLASS2CONSTRUCTOR = new IdentityHashMap<>();

    @Test
    void testApplyWithRecordClass() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = Schema.of();
        Class<?> recordClass = MyRecord.class;
        Constructor<?> recordConstructor = recordClass.getDeclaredConstructor(String.class);
        CLASS2CONSTRUCTOR.put(recordClass, recordConstructor);

        Object result = transformer.apply("test", schema);

        assertNotNull(result);
        assertTrue(result instanceof MyRecord);
    }

    @Test
    void testApplyWithNonRecordClassAndParameterlessConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = Schema.of();
        Class<?> nonRecordClass = MyNonRecordClass.class;
        Constructor<?> primaryConstructor = nonRecordClass.getDeclaredConstructor();
        CLASS2CONSTRUCTOR.put(nonRecordClass, primaryConstructor);

        Object result = transformer.apply(nonRecordClass, schema);

        assertNotNull(result);
        assertTrue(result instanceof MyNonRecordClass);
    }

    @Test
    void testApplyWithNonRecordClassAndNoParameterlessConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = Schema.of();
        Class<?> nonRecordClass = MyNonRecordClassWithParams.class;
        Constructor<?> primaryConstructor = nonRecordClass.getDeclaredConstructor(String.class);
        CLASS2CONSTRUCTOR.put(nonRecordClass, primaryConstructor);

        Object result = transformer.apply("test", schema);

        assertNotNull(result);
        assertTrue(result instanceof MyNonRecordClassWithParams);
    }

    @Test
    void testApplyWithException() {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Schema<Object, ?> schema = Schema.of();
        Class<?> invalidClass = InvalidClass.class;

        assertThrows(RuntimeException.class, () -> transformer.apply(invalidClass, schema));
    }

    private static class MyRecord {
        public MyRecord(String s) {}
    }

    private static class MyNonRecordClass {
        public MyNonRecordClass() {}
    }

    private static class MyNonRecordClassWithParams {
        public MyNonRecordClassWithParams(String s) {}
    }

    private static class InvalidClass {
        private InvalidClass() {}
    }
}
