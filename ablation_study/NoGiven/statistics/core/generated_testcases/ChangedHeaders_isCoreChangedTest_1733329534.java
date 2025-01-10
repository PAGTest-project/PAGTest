
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.headers.Header;

public class ChangedHeaders_isCoreChangedTest {

    @Test
    public void testIsCoreChanged_NoChanges() {
        // Given
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = mock(DiffContext.class);
        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
        changedHeaders.setIncreased(new HashMap<>()).setMissing(new HashMap<>());

        // When
        DiffResult result = changedHeaders.isCoreChanged();

        // Then
        assertEquals(DiffResult.NO_CHANGES, result);
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        // Given
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = mock(DiffContext.class);
        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
        Map<String, Header> increased = new HashMap<>();
        increased.put("header1", new Header());
        changedHeaders.setIncreased(increased).setMissing(new HashMap<>());

        // When
        DiffResult result = changedHeaders.isCoreChanged();

        // Then
        assertEquals(DiffResult.COMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible() {
        // Given
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = mock(DiffContext.class);
        when(RESPONSE_HEADERS_DECREASED.enabled(context)).thenReturn(true);
        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
        Map<String, Header> missing = new HashMap<>();
        missing.put("header1", new Header());
        changedHeaders.setIncreased(new HashMap<>()).setMissing(missing);

        // When
        DiffResult result = changedHeaders.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }
}
