
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedPaths;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PathsDiff_diffTest {

    @Test
    public void testDiff_SuccessfulMatch() {
        // Given
        OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
        PathsDiff pathsDiff = new PathsDiff(openApiDiff);

        Map<String, PathItem> left = new HashMap<>();
        PathItem leftPath = new PathItem();
        left.put("/path1", leftPath);

        Map<String, PathItem> right = new HashMap<>();
        PathItem rightPath = new PathItem();
        right.put("/path1", rightPath);

        // When
        DeferredChanged<ChangedPaths> result = pathsDiff.diff(left, right);

        // Then
        verify(openApiDiff, times(1)).getPathDiff();
    }

    @Test
    public void testDiff_ExceptionThrown() {
        // Given
        OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
        PathsDiff pathsDiff = new PathsDiff(openApiDiff);

        Map<String, PathItem> left = new HashMap<>();
        PathItem leftPath = new PathItem();
        left.put("/path1", leftPath);

        Map<String, PathItem> right = new HashMap<>();
        PathItem rightPath1 = new PathItem();
        PathItem rightPath2 = new PathItem();
        right.put("/path1", rightPath1);
        right.put("/path2", rightPath2);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> pathsDiff.diff(left, right));
    }
}
