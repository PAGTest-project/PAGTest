
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testGetClassLoaderWithContextClassLoader() {
        validator.setUseContextClassLoader(true);
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        assertEquals(contextClassLoader, validator.getClassLoader());
    }

    @Test
    public void testGetClassLoaderWithDefaultClassLoader() {
        assertEquals(Validator.class.getClassLoader(), validator.getClassLoader());
    }

    @Test
    public void testGetClassLoaderWithNullContextClassLoader() {
        validator.setUseContextClassLoader(true);
        ClassLoader originalContextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(null);
            assertEquals(Validator.class.getClassLoader(), validator.getClassLoader());
        } finally {
            Thread.currentThread().setContextClassLoader(originalContextClassLoader);
        }
    }
}
