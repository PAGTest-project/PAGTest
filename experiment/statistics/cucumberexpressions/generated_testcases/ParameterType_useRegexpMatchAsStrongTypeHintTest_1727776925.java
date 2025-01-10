
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParameterType_useRegexpMatchAsStrongTypeHintTest {

    @Test
    public void testUseRegexpMatchAsStrongTypeHintTrue() {
        ParameterType<Object> parameterType = new ParameterType<>(
                "name",
                singletonList("regexp"),
                Object.class,
                new CaptureGroupTransformer<Object>() {
                    @Override
                    public Object transform(String[] args) {
                        return null;
                    }
                },
                true,
                false,
                true
        );
        assertTrue(parameterType.useRegexpMatchAsStrongTypeHint());
    }

    @Test
    public void testUseRegexpMatchAsStrongTypeHintFalse() {
        ParameterType<Object> parameterType = new ParameterType<>(
                "name",
                singletonList("regexp"),
                Object.class,
                new CaptureGroupTransformer<Object>() {
                    @Override
                    public Object transform(String[] args) {
                        return null;
                    }
                },
                true,
                false,
                false
        );
        assertFalse(parameterType.useRegexpMatchAsStrongTypeHint());
    }
}
