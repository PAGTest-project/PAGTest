
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
        // Given
        Object bean = new Object() {
            public String getSomeProperty() {
                return "someValue";
            }
        };
        String property = "someProperty";
        String expectedValue = "someValue";

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

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertNull(result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyStringArray() throws Exception {
        // Given
        Object bean = new Object() {
            public String[] getSomeProperty() {
                return new String[0];
            }
        };
        String property = "someProperty";

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsNonEmptyStringArray() throws Exception {
        // Given
        Object bean = new Object() {
            public String[] getSomeProperty() {
                return new String[]{"value1", "value2"};
            }
        };
        String property = "someProperty";
        String expectedValue = "[value1, value2]";

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(expectedValue, result);
    }

    @Test
    public void testGetValueAsString_PropertyIsEmptyCollection() throws Exception {
        // Given
        Object bean = new Object() {
            public Collection<?> getSomeProperty() {
                return Collections.emptyList();
            }
        };
        String property = "someProperty";

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals("", result);
    }

    @Test
    public void testGetValueAsString_PropertyIsNonEmptyCollection() throws Exception {
        // Given
        Object bean = new Object() {
            public Collection<?> getSomeProperty() {
                return Collections.singletonList("value");
            }
        };
        String property = "someProperty";
        String expectedValue = "[value]";

        // When
        String result = ValidatorUtils.getValueAsString(bean, property);

        // Then
        assertEquals(expectedValue, result);
    }
}
