
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Collections;

public class ValidatorUtils_getValueAsStringTest {

    @Test
    public void testGetValueAsStringWithNullValue() {
        Object bean = new Object();
        String property = "nonExistentProperty";
        assertNull(ValidatorUtils.getValueAsString(bean, property));
    }

    @Test
    public void testGetValueAsStringWithStringValue() {
        Object bean = new TestBean("testValue");
        String property = "testProperty";
        assertEquals("testValue", ValidatorUtils.getValueAsString(bean, property));
    }

    @Test
    public void testGetValueAsStringWithEmptyStringArray() {
        Object bean = new TestBean(new String[]{});
        String property = "testProperty";
        assertEquals("", ValidatorUtils.getValueAsString(bean, property));
    }

    @Test
    public void testGetValueAsStringWithNonEmptyStringArray() {
        Object bean = new TestBean(new String[]{"value1", "value2"});
        String property = "testProperty";
        assertEquals("[value1, value2]", ValidatorUtils.getValueAsString(bean, property));
    }

    @Test
    public void testGetValueAsStringWithEmptyCollection() {
        Object bean = new TestBean(Collections.emptyList());
        String property = "testProperty";
        assertEquals("", ValidatorUtils.getValueAsString(bean, property));
    }

    @Test
    public void testGetValueAsStringWithNonEmptyCollection() {
        Object bean = new TestBean(Collections.singletonList("value1"));
        String property = "testProperty";
        assertEquals("[value1]", ValidatorUtils.getValueAsString(bean, property));
    }

    private static class TestBean {
        private final Object testProperty;

        public TestBean(Object testProperty) {
            this.testProperty = testProperty;
        }

        public Object getTestProperty() {
            return testProperty;
        }
    }
}
