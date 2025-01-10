
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChangedHeader_isCoreChangedTest {

    @Test
    public void testIsCoreChanged_NoChanges() {
        Header oldHeader = new Header();
        Header newHeader = new Header();
        DiffContext context = mock(DiffContext.class);

        ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context);
        assertEquals(DiffResult.NO_CHANGES, changedHeader.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Incompatible_Explode() {
        Header oldHeader = new Header();
        Header newHeader = new Header();
        DiffContext context = mock(DiffContext.class);
        when(RESPONSE_HEADER_EXPLODE_CHANGED.enabled(context)).thenReturn(true);

        ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context)
                .setExplode(true);
        assertEquals(DiffResult.INCOMPATIBLE, changedHeader.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Incompatible_RequiredDecreased() {
        Header oldHeader = new Header();
        oldHeader.setRequired(true);
        Header newHeader = new Header();
        newHeader.setRequired(false);
        DiffContext context = mock(DiffContext.class);
        when(RESPONSE_HEADER_REQUIRED_DECREASED.enabled(context)).thenReturn(true);

        ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context)
                .setRequired(true);
        assertEquals(DiffResult.INCOMPATIBLE, changedHeader.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Incompatible_RequiredIncreased() {
        Header oldHeader = new Header();
        oldHeader.setRequired(false);
        Header newHeader = new Header();
        newHeader.setRequired(true);
        DiffContext context = mock(DiffContext.class);
        when(RESPONSE_HEADER_REQUIRED_INCREASED.enabled(context)).thenReturn(true);

        ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context)
                .setRequired(true);
        assertEquals(DiffResult.INCOMPATIBLE, changedHeader.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Incompatible_Style() {
        Header oldHeader = new Header();
        Header newHeader = new Header();
        DiffContext context = mock(DiffContext.class);

        ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context)
                .setStyle(true);
        assertEquals(DiffResult.INCOMPATIBLE, changedHeader.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        Header oldHeader = new Header();
        Header newHeader = new Header();
        DiffContext context = mock(DiffContext.class);

        ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context)
                .setRequired(true)
                .setDeprecated(true)
                .setStyle(false)
                .setExplode(false);
        assertEquals(DiffResult.COMPATIBLE, changedHeader.isCoreChanged());
    }
}
