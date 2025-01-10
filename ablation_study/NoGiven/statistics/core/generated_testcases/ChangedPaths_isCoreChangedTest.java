
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import io.swagger.v3.oas.models.PathItem;
import org.mockito.Mockito;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.mockito.MockitoAnnotations;

public class ChangedPaths_isCoreChangedTest {

    private ChangedPaths changedPaths;
    private OpenApiDiffOptions options;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Map<String, PathItem> oldPathMap = new HashMap<>();
        Map<String, PathItem> newPathMap = new HashMap<>();
        options = mock(OpenApiDiffOptions.class);
        changedPaths = new ChangedPaths(oldPathMap, newPathMap, options);
    }

    @Test
    public void testIsCoreChanged_NoChanges() {
        // Given
        changedPaths.setIncreased(new HashMap<>());
        changedPaths.setMissing(new HashMap<>());

        // When
        DiffResult result = changedPaths.isCoreChanged();

        // Then
        assertEquals(DiffResult.NO_CHANGES, result);
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        // Given
        Map<String, PathItem> increased = new HashMap<>();
        increased.put("path1", new PathItem());
        changedPaths.setIncreased(increased);
        changedPaths.setMissing(new HashMap<>());

        // When
        DiffResult result = changedPaths.isCoreChanged();

        // Then
        assertEquals(DiffResult.COMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible() {
        // Given
        Map<String, PathItem> missing = new HashMap<>();
        missing.put("path1", new PathItem());
        changedPaths.setIncreased(new HashMap<>());
        changedPaths.setMissing(missing);
        CompositeConfiguration config = new CompositeConfiguration();
        when(options.getConfig()).thenReturn(config);

        // When
        DiffResult result = changedPaths.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }
}
