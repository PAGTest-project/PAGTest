
package org.apache.commons.validator.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        PropertyUtils.setProperty(bean, property, stringArray);

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
        PropertyUtils.setProperty(bean, property, emptyStringArray);

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
        PropertyUtils.setProperty(bean, property, collection);

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
        PropertyUtils.setProperty(bean, property, emptyCollection);

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
        PropertyUtils.setProperty(bean, property, stringValue);

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(stringValue, result);
    }
}
