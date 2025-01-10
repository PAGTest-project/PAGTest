
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_updateTLDOverrideTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        // Ensure inUse is false before each test
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_PLUS);
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testUpdateTLDOverride_ValidTLDs() {
        // Given
        String[] tlds = {"local", "pvt"};

        // When
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_PLUS, tlds);

        // Then
        assertTrue(validator.isValidGenericTld("local"));
        assertTrue(validator.isValid("abc.local"));
        assertTrue(validator.isValidGenericTld("pvt"));
        assertTrue(validator.isValid("abc.pvt"));
    }

    @Test
    public void testUpdateTLDOverride_InvalidTLDs() {
        // Given
        String[] tlds = {"invalid1", "invalid2"};

        // When
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_PLUS, tlds);

        // Then
        assertFalse(validator.isValidGenericTld("invalid1"));
        assertFalse(validator.isValid("abc.invalid1"));
        assertFalse(validator.isValidGenericTld("invalid2"));
        assertFalse(validator.isValid("abc.invalid2"));
    }

    @Test
    public void testUpdateTLDOverride_IllegalStateException() {
        // Given
        DomainValidator.getInstance(true); // inUse is now true
        String[] tlds = {"local", "pvt"};

        // When & Then
        assertThrows(IllegalStateException.class, () -> {
            DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_PLUS, tlds);
        });
    }

    @Test
    public void testUpdateTLDOverride_IllegalArgumentException() {
        // Given
        String[] tlds = {"local", "pvt"};

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_RO, tlds);
        });
    }

    @Test
    public void testUpdateTLDOverride_EmptyTLDs() {
        // Given
        String[] tlds = {};

        // When
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_PLUS, tlds);

        // Then
        // No exception should be thrown, and no changes should be made
        assertFalse(validator.isValidGenericTld("local"));
        assertFalse(validator.isValid("abc.local"));
    }
}
