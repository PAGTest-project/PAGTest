
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class ChangedNumericRange_equalsTest {

    @Test
    public void testEquals_SameObject() {
        ChangedNumericRange range = new ChangedNumericRange(
            BigDecimal.ONE, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN,
            true, true, true, true, new DiffContext(null)
        );
        assertTrue(range.equals(range));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedNumericRange range = new ChangedNumericRange(
            BigDecimal.ONE, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN,
            true, true, true, true, new DiffContext(null)
        );
        assertFalse(range.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedNumericRange range = new ChangedNumericRange(
            BigDecimal.ONE, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN,
            true, true, true, true, new DiffContext(null)
        );
        assertFalse(range.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentValues() {
        ChangedNumericRange range1 = new ChangedNumericRange(
            BigDecimal.ONE, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN,
            true, true, true, true, new DiffContext(null)
        );
        ChangedNumericRange range2 = new ChangedNumericRange(
            BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
            false, false, false, false, new DiffContext(null)
        );
        assertFalse(range1.equals(range2));
    }

    @Test
    public void testEquals_SameValues() {
        ChangedNumericRange range1 = new ChangedNumericRange(
            BigDecimal.ONE, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN,
            true, true, true, true, new DiffContext(null)
        );
        ChangedNumericRange range2 = new ChangedNumericRange(
            BigDecimal.ONE, BigDecimal.ONE, BigDecimal.TEN, BigDecimal.TEN,
            true, true, true, true, new DiffContext(null)
        );
        assertTrue(range1.equals(range2));
    }
}
