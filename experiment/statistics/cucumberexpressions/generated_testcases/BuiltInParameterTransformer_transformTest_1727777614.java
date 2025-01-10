
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BuiltInParameterTransformer_transformTest {

    private BuiltInParameterTransformer transformer;

    @BeforeEach
    void setUp() {
        transformer = new BuiltInParameterTransformer(Locale.ENGLISH);
    }

    @Test
    void testTransformStringToBigDecimal() {
        String fromValue = "123.45";
        Type toValueType = BigDecimal.class;
        Object result = transformer.transform(fromValue, toValueType);
        assertEquals(new BigDecimal("123.45"), result);
    }

    @Test
    void testTransformStringToOptionalBigDecimal() {
        String fromValue = "123.45";
        Type toValueType = Optional.class;
        Object result = transformer.transform(fromValue, toValueType);
        assertEquals(Optional.of(new BigDecimal("123.45")), result);
    }

    @Test
    void testTransformStringToUnsupportedType() {
        String fromValue = "test";
        Type toValueType = this.getClass();
        assertThrows(IllegalArgumentException.class, () -> {
            transformer.transform(fromValue, toValueType);
        });
    }

    @Test
    void testTransformNullValue() {
        String fromValue = null;
        Type toValueType = BigDecimal.class;
        Object result = transformer.transform(fromValue, toValueType);
        assertNull(result);
    }

    @Test
    void testTransformStringToEnum() {
        String fromValue = "VALUE1";
        Type toValueType = TestEnum.class;
        Object result = transformer.transform(fromValue, toValueType);
        assertEquals(TestEnum.VALUE1, result);
    }

    private enum TestEnum {
        VALUE1, VALUE2
    }
}
