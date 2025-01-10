
package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.model.Change;
import org.openapitools.openapidiff.core.model.Changed;
import org.openapitools.openapidiff.core.model.ChangedExtensions;
import org.openapitools.openapidiff.core.model.DiffContext;

public class ExtensionsDiff_diffTest {

    @Mock
    private OpenApiDiff openApiDiff;

    @Mock
    private DiffContext context;

    private ExtensionsDiff extensionsDiff;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        extensionsDiff = new ExtensionsDiff(openApiDiff);
    }

    @Test
    public void testDiff_AllPathsCovered() {
        // Given
        Map<String, Object> left = new HashMap<>();
        left.put("key1", "value1");
        left.put("key2", "value2");

        Map<String, Object> right = new HashMap<>();
        right.put("key1", "value1");
        right.put("key3", "value3");

        // Mocking executeExtensionDiff to return a Changed object
        Changed changed = mock(Changed.class);
        when(changed.isDifferent()).thenReturn(true);

        when(extensionsDiff.executeExtensionDiff(anyString(), any(Change.class), any(DiffContext.class)))
            .thenReturn(Optional.of(changed));

        // When
        Optional<ChangedExtensions> result = extensionsDiff.diff(left, right, context);

        // Then
        assertTrue(result.isPresent());
        ChangedExtensions changedExtensions = result.get();
        assertTrue(changedExtensions.getChanged().containsKey("key1"));
        assertTrue(changedExtensions.getMissing().containsKey("key2"));
        assertTrue(changedExtensions.getIncreased().containsKey("key3"));
    }
}
