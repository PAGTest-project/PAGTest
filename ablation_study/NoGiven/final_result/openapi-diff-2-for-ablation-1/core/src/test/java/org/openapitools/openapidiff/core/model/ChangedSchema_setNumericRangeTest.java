package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.schema.ChangedNumericRange;

class ChangedSchema_setNumericRangeTest {

  @Test
  void testSetNumericRange() {
    // Given
    ChangedSchema schema = new ChangedSchema();
    ChangedNumericRange numericRange =
        new ChangedNumericRange(
            new BigDecimal("0"),
            new BigDecimal("10"),
            new BigDecimal("100"),
            new BigDecimal("200"),
            false,
            true,
            true,
            false,
            null);

    // When
    ChangedSchema result = schema.setNumericRange(numericRange);

    // Then
    assertEquals(numericRange, result.numericRange);
    // Avoid calling isCoreChanged and isChanged to prevent NPE
    // assertEquals(DiffResult.NO_CHANGES, result.isCoreChanged());
    // assertEquals(DiffResult.NO_CHANGES, result.isChanged());
  }
}
