
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Argument_getValueTest {

    @Test
    public void testGetValue() {
        // Given
        Group mockGroup = mock(Group.class);
        ParameterType<String> mockParameterType = mock(ParameterType.class);
        List<String> values = Arrays.asList("value1", "value2");

        when(mockGroup.getValues()).thenReturn(values);
        when(mockParameterType.transform(values)).thenReturn("transformedValue");

        Argument<String> argument = Argument.build(mockGroup, Arrays.asList(mockParameterType)).get(0);

        // When
        String result = argument.getValue();

        // Then
        assertEquals("transformedValue", result);
        verify(mockGroup).getValues();
        verify(mockParameterType).transform(values);
    }
}
