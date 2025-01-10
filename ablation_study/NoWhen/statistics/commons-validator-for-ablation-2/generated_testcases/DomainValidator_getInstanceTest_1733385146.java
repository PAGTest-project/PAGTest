
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_getInstanceTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(false);
    }

    @Test
    public void testGetInstanceAllowLocal() {
        DomainValidator instanceWithLocal = DomainValidator.getInstance(true);
        assertTrue(instanceWithLocal.isValid("localhost"));
        assertTrue(instanceWithLocal.isValid("localdomain"));

        DomainValidator instanceWithoutLocal = DomainValidator.getInstance(false);
        assertFalse(instanceWithoutLocal.isValid("localhost"));
        assertFalse(instanceWithoutLocal.isValid("localdomain"));
    }

    @Test
    public void testGetInstanceAllowLocalWithTLDOverride() {
        DomainValidator.updateTLDOverride(ArrayType.LOCAL_PLUS, "example");
        DomainValidator instanceWithLocal = DomainValidator.getInstance(true);
        assertTrue(instanceWithLocal.isValid("test.example"));

        DomainValidator.updateTLDOverride(ArrayType.LOCAL_MINUS, "example");
        DomainValidator instanceWithoutLocal = DomainValidator.getInstance(false);
        assertFalse(instanceWithoutLocal.isValid("test.example"));
    }

    @Test
    public void testGetInstanceWithCustomTLDs() {
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_PLUS, "custom"));
        DomainValidator customValidator = DomainValidator.getInstance(false, items);
        assertTrue(customValidator.isValid("test.custom"));

        items.clear();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS, "com"));
        customValidator = DomainValidator.getInstance(false, items);
        assertFalse(customValidator.isValid("test.com"));
    }

    @Test
    public void testGetInstanceWithInvalidTLDs() {
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_PLUS, "invalid"));
        DomainValidator customValidator = DomainValidator.getInstance(false, items);
        assertFalse(customValidator.isValid("test.invalid"));
    }

    @Test
    public void testGetInstanceWithMixedTLDs() {
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_PLUS, "custom"));
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS, "com"));
        DomainValidator customValidator = DomainValidator.getInstance(false, items);
        assertTrue(customValidator.isValid("test.custom"));
        assertFalse(customValidator.isValid("test.com"));
    }
}
