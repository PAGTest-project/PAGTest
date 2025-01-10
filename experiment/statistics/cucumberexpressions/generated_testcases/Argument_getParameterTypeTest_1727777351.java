
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Argument_getParameterTypeTest {

    @Test
    public void testGetParameterType() {
        // Given
        ParameterType<String> mockParameterType = mock(ParameterType.class);
        Group mockGroup = mock(Group.class);
        Argument<String> argument = new Argument<>(mockGroup, mockParameterType);

        // When
        ParameterType<String> result = argument.getParameterType();

        // Then
        assertEquals(mockParameterType, result);
    }
}
