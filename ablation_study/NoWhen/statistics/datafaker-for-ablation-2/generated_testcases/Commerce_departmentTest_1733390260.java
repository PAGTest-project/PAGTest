
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Commerce_departmentTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        commerce = new Commerce(new BaseProviders());
    }

    @Test
    void testDepartmentSingleDepartment() {
        String department = commerce.department();
        assertNotNull(department);
        assertTrue(department.matches("\\w+"));
    }

    @Test
    void testDepartmentMultipleDepartments() {
        String department = commerce.department();
        assertNotNull(department);
        assertTrue(department.matches("(\\w+, )*\\w+ & \\w+"));
    }
}
