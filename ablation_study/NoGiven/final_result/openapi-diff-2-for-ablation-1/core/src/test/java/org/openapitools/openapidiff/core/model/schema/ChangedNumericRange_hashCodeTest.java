package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;

public class ChangedNumericRange_hashCodeTest {

  @Test
  public void testHashCode() {
    DiffContext context = new DiffContext(null);
    ChangedNumericRange range1 =
        new ChangedNumericRange(
            new BigDecimal("1.0"),
            new BigDecimal("1.0"),
            new BigDecimal("10.0"),
            new BigDecimal("10.0"),
            true,
            true,
            true,
            true,
            context);
    ChangedNumericRange range2 =
        new ChangedNumericRange(
            new BigDecimal("1.0"),
            new BigDecimal("1.0"),
            new BigDecimal("10.0"),
            new BigDecimal("10.0"),
            true,
            true,
            true,
            true,
            context);
    ChangedNumericRange range3 =
        new ChangedNumericRange(
            new BigDecimal("2.0"),
            new BigDecimal("2.0"),
            new BigDecimal("10.0"),
            new BigDecimal("10.0"),
            true,
            true,
            true,
            true,
            context);

    assertEquals(range1.hashCode(), range2.hashCode());
    assertNotEquals(range1.hashCode(), range3.hashCode());
  }
}
