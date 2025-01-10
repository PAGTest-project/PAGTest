
package org.apache.commons.validator.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ValidatorUtils_getValueAsStringTest {

    @Test
    public void testGetValueAsString_PropertyFound() throws Exception {
        Object bean = new Object();
        String property = "someProperty";
        String expectedValue = "someValue";

        PropertyUtils.setProperty(bean, property, expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue, result);
    }

    @Test
    public void testGetValueAsString_PropertyNotFound() throws Exception {
        Object bean = new Object();
        String property = "nonExistentProperty";

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsNull() throws Exception {
        Object bean = new Object();
        String property = "nullProperty";

        PropertyUtils.setProperty(bean, property, null);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsStringArray() throws Exception {
        Object bean = new Object();
        String property = "stringArrayProperty";
        String[] stringArray = {"value1", "value2"};

        PropertyUtils.setProperty(bean, property, stringArray);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(stringArray.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyStringArray() throws Exception {
        Object bean = new Object();
        String property = "emptyStringArrayProperty";
        String[] emptyStringArray = {};

        PropertyUtils.setProperty(bean, property, emptyStringArray);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsCollection() throws Exception {
        Object bean = new Object();
        String property = "collectionProperty";
        Collection<String> collection = Collections.singletonList("value1");

        PropertyUtils.setProperty(bean, property, collection);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(collection.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyCollection() throws Exception {
        Object bean = new Object();
        String property = "emptyCollectionProperty";
        Collection<String> emptyCollection = Collections.emptyList();

        PropertyUtils.setProperty(bean, property, emptyCollection);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }
}
