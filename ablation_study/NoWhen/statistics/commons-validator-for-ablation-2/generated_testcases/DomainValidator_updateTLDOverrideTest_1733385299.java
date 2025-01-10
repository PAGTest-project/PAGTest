
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_updateTLDOverrideTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(false);
    }

    @Test
    public void testUpdateTLDOverrideValid() {
        String[] tlds = {"example1", "example2"};
        validator.updateTLDOverride(ArrayType.GENERIC_PLUS, tlds);
        assertTrue(validator.isValidTld("example1"));
        assertTrue(validator.isValidTld("example2"));
    }

    @Test
    public void testUpdateTLDOverrideInvalid() {
        String[] tlds = {"example1", "example2"};
        assertThrows(IllegalStateException.class, () -> {
            validator.updateTLDOverride(ArrayType.GENERIC_PLUS, tlds);
        });
    }

    @Test
    public void testUpdateTLDOverrideIllegalArgument() {
        String[] tlds = {"example1", "example2"};
        assertThrows(IllegalArgumentException.class, () -> {
            validator.updateTLDOverride(ArrayType.GENERIC_RO, tlds);
        });
    }

    @Test
    public void testUpdateTLDOverrideAfterGetInstance() {
        DomainValidator.getInstance(true);
        String[] tlds = {"example1", "example2"};
        assertThrows(IllegalStateException.class, () -> {
            validator.updateTLDOverride(ArrayType.GENERIC_PLUS, tlds);
        });
    }

    @Test
    public void testUpdateTLDOverrideLowerCase() {
        String[] tlds = {"Example1", "Example2"};
        validator.updateTLDOverride(ArrayType.GENERIC_PLUS, tlds);
        assertTrue(validator.isValidTld("example1"));
        assertTrue(validator.isValidTld("example2"));
    }

    @Test
    public void testUpdateTLDOverrideSorting() {
        String[] tlds = {"example2", "example1"};
        validator.updateTLDOverride(ArrayType.GENERIC_PLUS, tlds);
        String[] updatedTLDs = validator.getOverrides(ArrayType.GENERIC_PLUS);
        assertEquals("example1", updatedTLDs[0]);
        assertEquals("example2", updatedTLDs[1]);
    }
}
