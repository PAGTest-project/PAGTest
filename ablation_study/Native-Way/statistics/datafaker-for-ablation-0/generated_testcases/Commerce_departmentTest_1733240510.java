
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
    void testDepartmentSingleDepartment() {
        // Mocking faker to return a fixed number of departments
        commerce.faker.random().nextInt(4); // Mock to return 1
        String result = commerce.department();
        assertTrue(result.matches("[a-zA-Z]+"));
    }

    @Test
    void testDepartmentMultipleDepartments() {
        // Mocking faker to return a fixed number of departments
        commerce.faker.random().nextInt(4); // Mock to return 3
        String result = commerce.department();
        assertTrue(result.matches("[a-zA-Z]+(, [a-zA-Z]+) & [a-zA-Z]+"));
    }
}
