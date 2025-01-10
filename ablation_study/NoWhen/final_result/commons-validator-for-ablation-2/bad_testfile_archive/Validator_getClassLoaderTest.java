
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Validator_getClassLoaderTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = new Validator(new ValidatorResources());
    }

    @Test
    public void testGetClassLoaderWithSetClassLoader() {
        ClassLoader customClassLoader = new ClassLoader() {};
        validator.setClassLoader(customClassLoader);
        assertEquals(customClassLoader, validator.getClassLoader());
    }

    @Test
    public void testGetClassLoaderWithUseContextClassLoader() {
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
}
