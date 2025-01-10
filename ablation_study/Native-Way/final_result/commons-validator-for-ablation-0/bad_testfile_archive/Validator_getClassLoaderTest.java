
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Validator_getClassLoaderTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorResources resources = new ValidatorResources();
        validator = new Validator(resources);
    }

    @Test
    public void testGetClassLoaderWithCustomClassLoader() {
        ClassLoader customClassLoader = new ClassLoader() {};
        validator.setClassLoader(customClassLoader);
        assertEquals(customClassLoader, validator.getClassLoader());
    }

    @Test
    public void testGetClassLoaderWithContextClassLoader() {
        validator.setUseContextClassLoader(true);
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        assertNotNull(contextClassLoader);
        assertEquals(contextClassLoader, validator.getClassLoader());
    }

    @Test
    public void testGetClassLoaderWithDefaultClassLoader() {
        ClassLoader defaultClassLoader = Validator.class.getClassLoader();
        assertEquals(defaultClassLoader, validator.getClassLoader());
    }

    @Test
    public void testGetClassLoaderWithNullContextClassLoader() {
        validator.setUseContextClassLoader(true);
        ClassLoader defaultClassLoader = Validator.class.getClassLoader();
        Thread.currentThread().setContextClassLoader(null);
        assertEquals(defaultClassLoader, validator.getClassLoader());
    }

    @Test
    public void testSetUseContextClassLoader() {
        validator.setUseContextClassLoader(true);
        assertTrue(validator.getUseContextClassLoader());
    }

    @Test
    public void testSetParameter() {
        validator.setParameter("testParam", "testValue");
        assertEquals("testValue", validator.getParameterValue("testParam"));
    }

    @Test
    public void testValidate() throws ValidatorException {
        ValidatorResources resources = new ValidatorResources();
        validator = new Validator(resources);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.getDefault());
        ValidatorResults results = validator.validate();
        assertNotNull(results);
    }
}
