
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Argument_getTypeTest {

    @Test
    public void testGetType() {
        // Given
        ParameterType<?> mockParameterType = mock(ParameterType.class);
        Type expectedType = String.class;
        when(mockParameterType.getType()).thenReturn(expectedType);

        Group mockGroup = mock(Group.class);
        Argument<?> argument = new Argument<>(mockGroup, mockParameterType);

        // When
        Type actualType = argument.getType();

        // Then
        assertEquals(expectedType, actualType);
    }
}
