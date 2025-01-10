
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.DiffResult;

public class ChangedNumericRange_isChangedTest {

    private DiffContext context;
    private ChangedNumericRange changedNumericRange;

    @BeforeEach
    public void setUp() {
        context = mock(DiffContext.class);
    }

    @Test
    public void testNoChanges() {
        changedNumericRange = new ChangedNumericRange(
            new BigDecimal("10"), new BigDecimal("10"),
            new BigDecimal("20"), new BigDecimal("20"),
            true, true,
            true, true,
            context
        );
        assertEquals(DiffResult.NO_CHANGES, changedNumericRange.isChanged());
    }

    @Test
    public void testCompatibleRequest() {
        when(context.isRequest()).thenReturn(true);
        when(context.isResponse()).thenReturn(false);
        changedNumericRange = new ChangedNumericRange(
            new BigDecimal("10"), new BigDecimal("10"),
            new BigDecimal("20"), new BigDecimal("20"),
            true, false,
            true, false,
            context
        );
        assertEquals(DiffResult.COMPATIBLE, changedNumericRange.isChanged());
    }

    @Test
    public void testIncompatibleRequest() {
        when(context.isRequest()).thenReturn(true);
        when(context.isResponse()).thenReturn(false);
        changedNumericRange = new ChangedNumericRange(
            new BigDecimal("10"), new BigDecimal("15"),
            new BigDecimal("20"), new BigDecimal("15"),
            false, false,
            false, false,
            context
        );
        assertEquals(DiffResult.INCOMPATIBLE, changedNumericRange.isChanged());
    }

    @Test
    public void testCompatibleResponse() {
        when(context.isRequest()).thenReturn(false);
        when(context.isResponse()).thenReturn(true);
        changedNumericRange = new ChangedNumericRange(
            new BigDecimal("10"), new BigDecimal("10"),
            new BigDecimal("20"), new BigDecimal("20"),
            false, true,
            false, true,
            context
        );
        assertEquals(DiffResult.COMPATIBLE, changedNumericRange.isChanged());
    }

    @Test
    public void testIncompatibleResponse() {
        when(context.isRequest()).thenReturn(false);
        when(context.isResponse()).thenReturn(true);
        changedNumericRange = new ChangedNumericRange(
            new BigDecimal("10"), new BigDecimal("5"),
            new BigDecimal("20"), new BigDecimal("25"),
            false, false,
            false, false,
            context
        );
        assertEquals(DiffResult.INCOMPATIBLE, changedNumericRange.isChanged());
    }
}
