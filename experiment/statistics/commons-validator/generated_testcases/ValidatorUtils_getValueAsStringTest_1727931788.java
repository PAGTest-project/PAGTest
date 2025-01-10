
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
    public void testGetValueAsString_PropertyFound_String() throws Exception {
        Object bean = new Object();
        String property = "someProperty";
        String expectedValue = "someValue";

        when(PropertyUtils.getProperty(bean, property)).thenReturn(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue, result);
    }

    @Test
    public void testGetValueAsString_PropertyFound_StringArray() throws Exception {
        Object bean = new Object();
        String property = "someProperty";
        String[] expectedValue = {"value1", "value2"};

        when(PropertyUtils.getProperty(bean, property)).thenReturn(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyFound_EmptyStringArray() throws Exception {
        Object bean = new Object();
        String property = "someProperty";
        String[] expectedValue = {};

        when(PropertyUtils.getProperty(bean, property)).thenReturn(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyFound_Collection() throws Exception {
        Object bean = new Object();
        String property = "someProperty";
        Collection<String> expectedValue = Collections.singletonList("value1");

        when(PropertyUtils.getProperty(bean, property)).thenReturn(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyFound_EmptyCollection() throws Exception {
        Object bean = new Object();
        String property = "someProperty";
        Collection<String> expectedValue = Collections.emptyList();

        when(PropertyUtils.getProperty(bean, property)).thenReturn(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyNotFound() throws Exception {
        Object bean = new Object();
        String property = "nonExistentProperty";

        when(PropertyUtils.getProperty(bean, property)).thenThrow(new ReflectiveOperationException());

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsNull() throws Exception {
        Object bean = new Object();
        String property = "someProperty";

        when(PropertyUtils.getProperty(bean, property)).thenReturn(null);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertNull(result);
    }
}
