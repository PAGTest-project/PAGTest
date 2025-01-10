
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_updateTLDOverrideTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testUpdateTLDOverride() {
        // Given
        String[] tlds = {"example1", "example2"};
        ArrayType table = ArrayType.GENERIC_PLUS;

        // When
        DomainValidator.updateTLDOverride(table, tlds);

        // Then
        String[] updatedTLDs = DomainValidator.getTLDEntries(table);
        assertArrayEquals(tlds, updatedTLDs, "TLD override should update the specified table");
    }

    @Test
    public void testUpdateTLDOverrideInvalidTable() {
        // Given
        String[] tlds = {"example1", "example2"};
        ArrayType table = ArrayType.GENERIC_RO;

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> DomainValidator.updateTLDOverride(table, tlds));
        assertEquals("Cannot update the table: " + table, exception.getMessage());
    }

    @Test
    public void testUpdateTLDOverrideInUse() {
        // Given
        String[] tlds = {"example1", "example2"};
        ArrayType table = ArrayType.GENERIC_PLUS;
        DomainValidator.inUse = true;

        // When & Then
        IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> DomainValidator.updateTLDOverride(table, tlds));
        assertEquals("Can only invoke this method before calling getInstance", exception.getMessage());
    }

    @Test
    public void testUpdateTLDOverrideCaseInsensitivity() {
        // Given
        String[] tlds = {"Example1", "Example2"};
        ArrayType table = ArrayType.GENERIC_PLUS;

        // When
        DomainValidator.updateTLDOverride(table, tlds);

        // Then
        String[] updatedTLDs = DomainValidator.getTLDEntries(table);
        assertArrayEquals(new String[]{"example1", "example2"}, updatedTLDs, "TLD override should be case-insensitive");
    }

    @Test
    public void testUpdateTLDOverrideSorting() {
        // Given
        String[] tlds = {"example2", "example1"};
        ArrayType table = ArrayType.GENERIC_PLUS;

        // When
        DomainValidator.updateTLDOverride(table, tlds);

        // Then
        String[] updatedTLDs = DomainValidator.getTLDEntries(table);
        assertArrayEquals(new String[]{"example1", "example2"}, updatedTLDs, "TLD override should sort the entries");
    }
}
