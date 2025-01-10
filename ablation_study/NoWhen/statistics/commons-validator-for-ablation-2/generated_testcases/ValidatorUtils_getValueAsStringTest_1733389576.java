
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
    public void testGetValueAsString_PropertyIsNull() throws Exception {
        // Given
        Object bean = new Object();
        String property = "nonExistentProperty";

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsStringArray() throws Exception {
        // Given
        Object bean = new Object();
        String property = "stringArrayProperty";
        String[] stringArray = {"value1", "value2"};
        when(PropertyUtils.getProperty(bean, property)).thenReturn(stringArray);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(stringArray.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyStringArray() throws Exception {
        // Given
        Object bean = new Object();
        String property = "emptyStringArrayProperty";
        String[] emptyStringArray = {};
        when(PropertyUtils.getProperty(bean, property)).thenReturn(emptyStringArray);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsCollection() throws Exception {
        // Given
        Object bean = new Object();
        String property = "collectionProperty";
        Collection<String> collection = Collections.singletonList("value");
        when(PropertyUtils.getProperty(bean, property)).thenReturn(collection);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(collection.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyCollection() throws Exception {
        // Given
        Object bean = new Object();
        String property = "emptyCollectionProperty";
        Collection<String> emptyCollection = Collections.emptyList();
        when(PropertyUtils.getProperty(bean, property)).thenReturn(emptyCollection);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsString() throws Exception {
        // Given
        Object bean = new Object();
        String property = "stringProperty";
        String stringValue = "value";
        when(PropertyUtils.getProperty(bean, property)).thenReturn(stringValue);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(stringValue, result);
    }
}
