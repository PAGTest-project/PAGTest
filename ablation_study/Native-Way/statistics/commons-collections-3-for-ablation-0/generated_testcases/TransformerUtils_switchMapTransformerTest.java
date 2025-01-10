
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TransformerUtils_switchMapTransformerTest {

    private static final Object cObject = new Object();
    private static final Object cString = "test";
    private static final Object cInteger = 123;

    private Map<Object, Transformer<Object, Object>> objectsAndTransformers;

    @BeforeEach
    public void setUp() {
        objectsAndTransformers = new HashMap<>();
        objectsAndTransformers.put(cObject, ConstantTransformer.constantTransformer(cObject));
        objectsAndTransformers.put(cString, ConstantTransformer.constantTransformer(cString));
        objectsAndTransformers.put(cInteger, ConstantTransformer.constantTransformer(cInteger));
    }

    @Test
    public void testSwitchMapTransformerWithValidMap() {
        Transformer<Object, Object> transformer = TransformerUtils.switchMapTransformer(objectsAndTransformers);

        assertAll("Valid transformations",
                () -> assertEquals(cObject, transformer.transform(cObject)),
                () -> assertEquals(cString, transformer.transform(cString)),
                () -> assertEquals(cInteger, transformer.transform(cInteger))
        );
    }

    @Test
    public void testSwitchMapTransformerWithNullInput() {
        objectsAndTransformers.put(null, NOPTransformer.nopTransformer());
        Transformer<Object, Object> transformer = TransformerUtils.switchMapTransformer(objectsAndTransformers);

        assertNull(transformer.transform(null));
    }

    @Test
    public void testSwitchMapTransformerWithDefaultTransformer() {
        Transformer<Object, Object> defaultTransformer = ConstantTransformer.constantTransformer("default");
        objectsAndTransformers.put(null, defaultTransformer);
        Transformer<Object, Object> transformer = TransformerUtils.switchMapTransformer(objectsAndTransformers);

        assertEquals("default", transformer.transform(new Object()));
    }

    @Test
    public void testSwitchMapTransformerWithExceptionTransformer() {
        objectsAndTransformers.put(cObject, ExceptionTransformer.exceptionTransformer());
        Transformer<Object, Object> transformer = TransformerUtils.switchMapTransformer(objectsAndTransformers);

        assertThrows(FunctorException.class, () -> transformer.transform(cObject));
    }

    @Test
    public void testSwitchMapTransformerWithNullMap() {
        assertThrows(NullPointerException.class, () -> TransformerUtils.switchMapTransformer(null));
    }

    @Test
    public void testSwitchMapTransformerWithNullTransformer() {
        objectsAndTransformers.put(cObject, null);
        assertThrows(NullPointerException.class, () -> TransformerUtils.switchMapTransformer(objectsAndTransformers));
    }
}
