
package org.apache.commons.validator.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ValidatorUtils_getValueAsStringTest {

    public static class TestBean {
        private String someProperty;
        private String nullProperty;
        private String[] stringArrayProperty;
        private String[] emptyStringArrayProperty;
        private Collection<String> collectionProperty;
        private Collection<String> emptyCollectionProperty;

        public String getSomeProperty() {
            return someProperty;
        }

        public void setSomeProperty(String someProperty) {
            this.someProperty = someProperty;
        }

        public String getNullProperty() {
            return nullProperty;
        }

        public void setNullProperty(String nullProperty) {
            this.nullProperty = nullProperty;
        }

        public String[] getStringArrayProperty() {
            return stringArrayProperty;
        }

        public void setStringArrayProperty(String[] stringArrayProperty) {
            this.stringArrayProperty = stringArrayProperty;
        }

        public String[] getEmptyStringArrayProperty() {
            return emptyStringArrayProperty;
        }

        public void setEmptyStringArrayProperty(String[] emptyStringArrayProperty) {
            this.emptyStringArrayProperty = emptyStringArrayProperty;
        }

        public Collection<String> getCollectionProperty() {
            return collectionProperty;
        }

        public void setCollectionProperty(Collection<String> collectionProperty) {
            this.collectionProperty = collectionProperty;
        }

        public Collection<String> getEmptyCollectionProperty() {
            return emptyCollectionProperty;
        }

        public void setEmptyCollectionProperty(Collection<String> emptyCollectionProperty) {
            this.emptyCollectionProperty = emptyCollectionProperty;
        }
    }

    @Test
    public void testGetValueAsString_PropertyFound() throws Exception {
        TestBean bean = new TestBean();
        String property = "someProperty";
        String expectedValue = "someValue";

        PropertyUtils.setProperty(bean, property, expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue, result);
    }

    @Test
    public void testGetValueAsString_PropertyNotFound() throws Exception {
        TestBean bean = new TestBean();
        String property = "nonExistentProperty";

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsNull() throws Exception {
        TestBean bean = new TestBean();
        String property = "nullProperty";

        PropertyUtils.setProperty(bean, property, null);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsStringArray() throws Exception {
        TestBean bean = new TestBean();
        String property = "stringArrayProperty";
        String[] stringArray = {"value1", "value2"};

        PropertyUtils.setProperty(bean, property, stringArray);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(stringArray.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyStringArray() throws Exception {
        TestBean bean = new TestBean();
        String property = "emptyStringArrayProperty";
        String[] emptyStringArray = {};

        PropertyUtils.setProperty(bean, property, emptyStringArray);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsCollection() throws Exception {
        TestBean bean = new TestBean();
        String property = "collectionProperty";
        Collection<String> collection = Collections.singletonList("value1");

        PropertyUtils.setProperty(bean, property, collection);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(collection.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyCollection() throws Exception {
        TestBean bean = new TestBean();
        String property = "emptyCollectionProperty";
        Collection<String> emptyCollection = Collections.emptyList();

        PropertyUtils.setProperty(bean, property, emptyCollection);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }
}
