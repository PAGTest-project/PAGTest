
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Commerce_departmentTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        commerce = new Commerce(faker);
    }

    @Test
    void testDepartmentSingle() {
        String department = commerce.department();
        assertTrue(department.matches("[\\w\\s]+"));
    }

    @Test
    void testDepartmentMultiple() {
        String department = commerce.department();
        assertTrue(department.matches("[\\w\\s]+(, [\\w\\s]+)* & [\\w\\s]+"));
    }
}
