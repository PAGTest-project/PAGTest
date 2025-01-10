
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Commerce_productNameTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        commerce = new Commerce(new BaseProviders() {});
    }

    @Test
    void testProductName() {
        String productName = commerce.productName();
        assertNotNull(productName);
        assertTrue(productName.contains(" "));
    }

    @Test
    void testMaterial() {
        String material = commerce.material();
        assertNotNull(material);
    }

    @Test
    void testDepartment() {
        String department = commerce.department();
        assertNotNull(department);
        assertTrue(department.matches("(\\w+(, | & )?){1,3}"));
    }
}
