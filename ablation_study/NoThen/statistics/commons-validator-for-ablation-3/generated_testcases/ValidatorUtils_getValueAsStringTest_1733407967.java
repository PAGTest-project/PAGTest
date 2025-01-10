
package org.apache.commons.validator.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class ValidatorUtils_getValueAsStringTest {

    @Test
    public void testGetValueAsString_PropertyFound() throws Exception {
        // Given
        Object bean = new Object();
        String property = "someProperty";
        String expectedValue = "someValue";
        when(PropertyUtils.getProperty(bean, property)).thenReturn(expectedValue);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(expectedValue, result);
    }

    @Test
    public void testGetValueAsString_PropertyNotFound() throws Exception {
        // Given
        Object bean = new Object();
        String property = "nonExistentProperty";
        when(PropertyUtils.getProperty(bean, property)).thenThrow(new ReflectiveOperationException());

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyStringArray() throws Exception {
        // Given
        Object bean = new Object();
        String property = "someProperty";
        String[] emptyArray = new String[0];
        when(PropertyUtils.getProperty(bean, property)).thenReturn(emptyArray);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsNonEmptyStringArray() throws Exception {
        // Given
        Object bean = new Object();
        String property = "someProperty";
        String[] nonEmptyArray = {"value1", "value2"};
        when(PropertyUtils.getProperty(bean, property)).thenReturn(nonEmptyArray);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(nonEmptyArray.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyCollection() throws Exception {
        // Given
        Object bean = new Object();
        String property = "someProperty";
        Collection<?> emptyCollection = Collections.emptyList();
        when(PropertyUtils.getProperty(bean, property)).thenReturn(emptyCollection);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsNonEmptyCollection() throws Exception {
        // Given
        Object bean = new Object();
        String property = "someProperty";
        Collection<?> nonEmptyCollection = Collections.singletonList("value");
        when(PropertyUtils.getProperty(bean, property)).thenReturn(nonEmptyCollection);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(nonEmptyCollection.toString(), result);
    }
}
