
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ChangedApiResponse_isCoreChangedTest {

    private ChangedApiResponse changedApiResponse;
    private DiffContext context;

    @Mock
    private DiffResult RESPONSE_RESPONSES_DECREASED;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ApiResponses oldApiResponses = new ApiResponses();
        ApiResponses newApiResponses = new ApiResponses();
        context = mock(DiffContext.class);
        changedApiResponse = new ChangedApiResponse(oldApiResponses, newApiResponses, context);
    }

    @Test
    public void testIsCoreChanged_NoChanges() {
        // Given
        Map<String, ApiResponse> increased = new HashMap<>();
        Map<String, ApiResponse> missing = new HashMap<>();
        changedApiResponse.setIncreased(increased).setMissing(missing);

        // When
        DiffResult result = changedApiResponse.isCoreChanged();

        // Then
        assertEquals(DiffResult.NO_CHANGES, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible() {
        // Given
        Map<String, ApiResponse> increased = new HashMap<>();
        Map<String, ApiResponse> missing = new HashMap<>();
        missing.put("key", new ApiResponse());
        changedApiResponse.setIncreased(increased).setMissing(missing);
        when(RESPONSE_RESPONSES_DECREASED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedApiResponse.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        // Given
        Map<String, ApiResponse> increased = new HashMap<>();
        Map<String, ApiResponse> missing = new HashMap<>();
        increased.put("key", new ApiResponse());
        changedApiResponse.setIncreased(increased).setMissing(missing);
        when(RESPONSE_RESPONSES_DECREASED.enabled(context)).thenReturn(false);

        // When
        DiffResult result = changedApiResponse.isCoreChanged();

        // Then
        assertEquals(DiffResult.COMPATIBLE, result);
    }
}
