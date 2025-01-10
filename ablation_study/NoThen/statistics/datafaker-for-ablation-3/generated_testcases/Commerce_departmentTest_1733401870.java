
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Commerce_departmentTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        commerce = new Commerce(new BaseProviders());
    }

    @Test
    void testDepartmentSingleDepartment() {
        String department = commerce.department();
        assertTrue(department.matches("[\\w\\s]+"));
    }

    @Test
    void testDepartmentMultipleDepartments() {
        String department = commerce.department();
        assertTrue(department.matches("[\\w\\s]+(, [\\w\\s]+)* & [\\w\\s]+"));
    }

    @Test
    void testProductName() {
        String productName = commerce.productName();
        assertTrue(productName.matches("(\\w+ ?){3,4}"));
    }

    @Test
    void testMaterial() {
        String material = commerce.material();
        assertTrue(material.matches("[\\w\\s]+"));
    }

    @Test
    void testPrice() {
        String price = commerce.price();
        assertTrue(price.matches("\\d+\\.\\d{2}"));
    }

    @Test
    void testPromotionCode() {
        String promotionCode = commerce.promotionCode();
        assertTrue(promotionCode.matches("[\\w\\s]+ \\d{6}"));
    }
}
