
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedHeaders.Header;
import static org.openapitools.openapidiff.core.model.BackwardIncompatibleProp.RESPONSE_HEADERS_DECREASED;

public class ChangedHeaders_isCoreChangedTest {

    private ChangedHeaders changedHeaders;
    private DiffContext context;
    private Map<String, ChangedHeaders.Header> oldHeaders;
    private Map<String, ChangedHeaders.Header> newHeaders;

    @BeforeEach
    public void setUp() {
        oldHeaders = new HashMap<>();
        newHeaders = new HashMap<>();
        context = mock(DiffContext.class);
        changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
    }

    @Test
    public void testIsCoreChanged_NoChanges() {
        changedHeaders.setIncreased(new HashMap<>()).setMissing(new HashMap<>());
        assertEquals(DiffResult.NO_CHANGES, changedHeaders.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        Map<String, ChangedHeaders.Header> increased = new HashMap<>();
        increased.put("header1", new ChangedHeaders.Header());
        changedHeaders.setIncreased(increased).setMissing(new HashMap<>());
        assertEquals(DiffResult.COMPATIBLE, changedHeaders.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Incompatible() {
        Map<String, ChangedHeaders.Header> missing = new HashMap<>();
        missing.put("header1", new ChangedHeaders.Header());
        changedHeaders.setIncreased(new HashMap<>()).setMissing(missing);
        when(RESPONSE_HEADERS_DECREASED.enabled(context)).thenReturn(true);
        assertEquals(DiffResult.INCOMPATIBLE, changedHeaders.isCoreChanged());
    }
}
