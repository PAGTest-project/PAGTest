
package org.ice4j;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StackProperties_getIntTest {

    @Test
    public void testGetInt_ValidInteger() {
        // Given
        String propertyName = "testProperty";
        int defaultValue = 10;
        String validInteger = "20";

        // Mocking getString to return a valid integer string
        StackProperties mockStackProperties = mock(StackProperties.class);
        when(mockStackProperties.getString(propertyName)).thenReturn(validInteger);

        // When
        int result = StackProperties.getInt(propertyName, defaultValue);

        // Then
        assertEquals(20, result);
    }

    @Test
    public void testGetInt_InvalidInteger() {
        // Given
        String propertyName = "testProperty";
        int defaultValue = 10;
        String invalidInteger = "notAnInteger";

        // Mocking getString to return an invalid integer string
        StackProperties mockStackProperties = mock(StackProperties.class);
        when(mockStackProperties.getString(propertyName)).thenReturn(invalidInteger);

        // When
        int result = StackProperties.getInt(propertyName, defaultValue);

        // Then
        assertEquals(defaultValue, result);
    }

    @Test
    public void testGetInt_NullString() {
        // Given
        String propertyName = "testProperty";
        int defaultValue = 10;

        // Mocking getString to return null
        StackProperties mockStackProperties = mock(StackProperties.class);
        when(mockStackProperties.getString(propertyName)).thenReturn(null);

        // When
        int result = StackProperties.getInt(propertyName, defaultValue);

        // Then
        assertEquals(defaultValue, result);
    }

    @Test
    public void testGetInt_EmptyString() {
        // Given
        String propertyName = "testProperty";
        int defaultValue = 10;
        String emptyString = "";

        // Mocking getString to return an empty string
        StackProperties mockStackProperties = mock(StackProperties.class);
        when(mockStackProperties.getString(propertyName)).thenReturn(emptyString);

        // When
        int result = StackProperties.getInt(propertyName, defaultValue);

        // Then
        assertEquals(defaultValue, result);
    }
}
