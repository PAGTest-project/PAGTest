
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class Commerce_departmentTest {
    private Commerce commerce;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        when(faker.random().nextInt(4)).thenReturn(1);
        commerce = new Commerce(faker);
    }

    @Test
    void testDepartmentSingleDepartment() {
        String result = commerce.department();
        assertTrue(result.matches("[a-zA-Z]+"));
    }

    @Test
    void testDepartmentMultipleDepartments() {
        when(faker.random().nextInt(4)).thenReturn(3);
        String result = commerce.department();
        assertTrue(result.matches("[a-zA-Z]+(, [a-zA-Z]+) & [a-zA-Z]+"));
    }
}
