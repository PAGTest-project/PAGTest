
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import org.openapitools.openapidiff.core.model.DiffContext;

public class ChangedNumericRange_toStringTest {

    @Test
    public void testToString() {
        DiffContext context = new DiffContext(null);
        ChangedNumericRange changedNumericRange = new ChangedNumericRange(
            new BigDecimal("1.0"), new BigDecimal("2.0"),
            new BigDecimal("3.0"), new BigDecimal("4.0"),
            true, false,
            true, false,
            context
        );

        String expected = "ChangedNumericRange("
            + "oldMinimumValue=1.0, newMinimumValue=2.0, "
            + "oldMaximumValue=3.0, newMaximumValue=4.0, "
            + "oldMinimumExclusiveValue=true, newMinimumExclusiveValue=false, "
            + "oldMaximumExclusiveValue=true, newMaximumExclusiveValue=false, "
            + "context=" + context + ')';

        assertEquals(expected, changedNumericRange.toString());
    }
}
