
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

    public static class TestBean {
        private String someProperty;
        private String[] somePropertyArray;
        private Collection<String> somePropertyCollection;

        public String getSomeProperty() {
            return someProperty;
        }

        public void setSomeProperty(String someProperty) {
            this.someProperty = someProperty;
        }

        public String[] getSomePropertyArray() {
            return somePropertyArray;
        }

        public void setSomePropertyArray(String[] somePropertyArray) {
            this.somePropertyArray = somePropertyArray;
        }

        public Collection<String> getSomePropertyCollection() {
            return somePropertyCollection;
        }

        public void setSomePropertyCollection(Collection<String> somePropertyCollection) {
            this.somePropertyCollection = somePropertyCollection;
        }
    }

    @Test
    public void testGetValueAsString_PropertyFound_String() throws Exception {
        TestBean bean = new TestBean();
        String property = "someProperty";
        String expectedValue = "someValue";

        bean.setSomeProperty(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue, result);
    }

    @Test
    public void testGetValueAsString_PropertyFound_StringArray() throws Exception {
        TestBean bean = new TestBean();
        String property = "somePropertyArray";
        String[] expectedValue = {"value1", "value2"};

        bean.setSomePropertyArray(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyFound_EmptyStringArray() throws Exception {
        TestBean bean = new TestBean();
        String property = "somePropertyArray";
        String[] expectedValue = {};

        bean.setSomePropertyArray(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyFound_Collection() throws Exception {
        TestBean bean = new TestBean();
        String property = "somePropertyCollection";
        Collection<String> expectedValue = Collections.singletonList("value1");

        bean.setSomePropertyCollection(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals(expectedValue.toString(), result);
    }

    @Test
    public void testGetValueAsString_PropertyFound_EmptyCollection() throws Exception {
        TestBean bean = new TestBean();
        String property = "somePropertyCollection";
        Collection<String> expectedValue = Collections.emptyList();

        bean.setSomePropertyCollection(expectedValue);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertEquals("", result);
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
        String property = "someProperty";

        bean.setSomeProperty(null);

        String result = ValidatorUtils.getValueAsString(bean, property);

        assertNull(result);
    }
}
