
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static java.util.Collections.singletonList;

public class ParameterType_preferForRegexpMatchTest {

    @Test
    public void testPreferForRegexpMatch_True() {
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
                true
        );
        assertTrue(parameterType.preferForRegexpMatch());
    }

    @Test
    public void testPreferForRegexpMatch_False() {
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
                false
        );
        assertFalse(parameterType.preferForRegexpMatch());
    }
}
