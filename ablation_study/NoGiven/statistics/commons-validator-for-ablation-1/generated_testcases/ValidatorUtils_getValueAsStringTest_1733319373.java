
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
        Object bean = new Object();
        String property = "someProperty";
        String expectedValue = "someValue";

        when(PropertyUtils.getProperty(bean, property)).thenReturn(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue, result);
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
        String property = "nullProperty";

        when(PropertyUtils.getProperty(bean, property)).thenReturn(null);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsStringArray() throws Exception {
        Object bean = new Object();
        String property = "stringArrayProperty";
        String[] stringArray = {"value1", "value2"};

        when(PropertyUtils.getProperty(bean, property)).thenReturn(stringArray);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(stringArray.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyStringArray() throws Exception {
        Object bean = new Object();
        String property = "emptyStringArrayProperty";
        String[] emptyStringArray = {};

        when(PropertyUtils.getProperty(bean, property)).thenReturn(emptyStringArray);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsCollection() throws Exception {
        Object bean = new Object();
        String property = "collectionProperty";
        Collection<String> collection = Collections.singletonList("value1");

        when(PropertyUtils.getProperty(bean, property)).thenReturn(collection);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(collection.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyCollection() throws Exception {
        Object bean = new Object();
        String property = "emptyCollectionProperty";
        Collection<String> emptyCollection = Collections.emptyList();

        when(PropertyUtils.getProperty(bean, property)).thenReturn(emptyCollection);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }
}
