
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Validator_getClassLoaderTest {

    @Test
    void testGetClassLoader_withClassLoaderSet() {
        Validator validator = new Validator(new ValidatorResources());
        ClassLoader customClassLoader = new ClassLoader() {};
        validator.setClassLoader(customClassLoader);
        assertEquals(customClassLoader, validator.getClassLoader());
    }

    @Test
    void testGetClassLoader_withContextClassLoader() {
        Validator validator = new Validator(new ValidatorResources());
        validator.setUseContextClassLoader(true);
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        assertEquals(contextClassLoader, validator.getClassLoader());
    }

    @Test
    void testGetClassLoader_withDefaultClassLoader() {
        Validator validator = new Validator(new ValidatorResources());
        validator.setUseContextClassLoader(false);
        ClassLoader defaultClassLoader = Validator.class.getClassLoader();
        assertEquals(defaultClassLoader, validator.getClassLoader());
    }
}
