
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.DiffResult;
import org.openapitools.openapidiff.core.model.BackwardIncompatibleProp;
import org.mockito.Mockito;

public class ChangedEnum_isItemsChangedTest {

    private DiffContext context;
    private ChangedEnum<String> changedEnum;

    @BeforeEach
    public void setUp() {
        context = mock(DiffContext.class);
    }

    @Test
    public void testIsItemsChanged_RequestEnumDecreased() {
        List<String> oldValue = Arrays.asList("A", "B", "C");
        List<String> newValue = Arrays.asList("A", "B");
        changedEnum = new ChangedEnum<>(oldValue, newValue, context);

        when(context.isRequest()).thenReturn(true);
        when(context.isResponse()).thenReturn(false);
        when(BackwardIncompatibleProp.REQUEST_ENUM_DECREASED.enabled(context)).thenReturn(true);

        assertEquals(DiffResult.INCOMPATIBLE, changedEnum.isItemsChanged());
    }

    @Test
    public void testIsItemsChanged_ResponseEnumIncreased() {
        List<String> oldValue = Arrays.asList("A", "B");
        List<String> newValue = Arrays.asList("A", "B", "C");
        changedEnum = new ChangedEnum<>(oldValue, newValue, context);

        when(context.isRequest()).thenReturn(false);
        when(context.isResponse()).thenReturn(true);
        when(BackwardIncompatibleProp.RESPONSE_ENUM_INCREASED.enabled(context)).thenReturn(true);

        assertEquals(DiffResult.INCOMPATIBLE, changedEnum.isItemsChanged());
    }

    @Test
    public void testIsItemsChanged_Compatible() {
        List<String> oldValue = Arrays.asList("A", "B");
        List<String> newValue = Arrays.asList("A", "B");
        changedEnum = new ChangedEnum<>(oldValue, newValue, context);

        when(context.isRequest()).thenReturn(false);
        when(context.isResponse()).thenReturn(false);

        assertEquals(DiffResult.COMPATIBLE, changedEnum.isItemsChanged());
    }
}
