
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParameterTypeRegistry_setDefaultParameterTransformerTest {

    private ParameterTypeRegistry parameterTypeRegistry;
    private ParameterByTypeTransformer mockTransformer;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = new ParameterTypeRegistry(Locale.ENGLISH);
        mockTransformer = new ParameterByTypeTransformer() {
            @Override
            public Object transform(String fromValue, Type toValueType) {
                return null;
            }
        };
    }

    @Test
    public void testSetDefaultParameterTransformer() {
        // Given
        ParameterByTypeTransformer originalTransformer = parameterTypeRegistry.getDefaultParameterTransformer();

        // When
        parameterTypeRegistry.setDefaultParameterTransformer(mockTransformer);

        // Then
        assertSame(mockTransformer, parameterTypeRegistry.getDefaultParameterTransformer());
    }
}
