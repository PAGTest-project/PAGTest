
package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.headers.Header;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.model.ChangedHeader;
import org.openapitools.openapidiff.core.model.ChangedHeaders;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredBuilder;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;

import java.util.List;

public class HeadersDiff_diffTest {

    @Mock
    private OpenApiDiff openApiDiff;

    @Mock
    private HeaderDiff headerDiff;

    private HeadersDiff headersDiff;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        headersDiff = new HeadersDiff(openApiDiff);
        when(openApiDiff.getHeaderDiff()).thenReturn(headerDiff);
    }

    @Test
    public void testDiff_WithSharedHeaders() {
        // Given
        Map<String, Header> left = new HashMap<>();
        left.put("header1", new Header());
        Map<String, Header> right = new HashMap<>();
        right.put("header1", new Header());
        DiffContext context = new DiffContext(null);

        MapKeyDiff<String, Header> mockMapKeyDiff = mock(MapKeyDiff.class);
        when(mockMapKeyDiff.getSharedKey()).thenReturn(List.of("header1"));
        when(mockMapKeyDiff.getIncreased()).thenReturn(new HashMap<>());
        when(mockMapKeyDiff.getMissing()).thenReturn(new HashMap<>());
        when(MapKeyDiff.diff(left, right)).thenReturn(mockMapKeyDiff);

        DeferredChanged<ChangedHeader> mockDeferredChanged = mock(DeferredChanged.class);
        when(headerDiff.diff(any(), any(), any())).thenReturn(mockDeferredChanged);
        when(mockDeferredChanged.isPresent()).thenReturn(true);
        when(mockDeferredChanged.get()).thenReturn(new ChangedHeader(new Header(), new Header(), context));

        // When
        DeferredChanged<ChangedHeaders> result = headersDiff.diff(left, right, context);

        // Then
        verify(headerDiff).diff(any(), any(), any());
        assertEquals(true, result.isPresent());
    }
}
