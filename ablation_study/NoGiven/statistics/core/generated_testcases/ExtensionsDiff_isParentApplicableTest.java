
package org.openapitools.openapidiff.core.compare;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Change;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ExtensionsDiff_isParentApplicableTest {

    @Test
    public void testIsParentApplicable_EmptyExtensions() {
        // Given
        ExtensionsDiff extensionsDiff = new ExtensionsDiff(null);
        Map<String, Object> extensions = new HashMap<>();
        DiffContext context = Mockito.mock(DiffContext.class);

        // When
        boolean result = extensionsDiff.isParentApplicable(Change.Type.ADDED, new Object(), extensions, context);

        // Then
        assertTrue(result);
    }

    @Test
    public void testIsParentApplicable_NonEmptyExtensions() {
        // Given
        ExtensionsDiff extensionsDiff = Mockito.spy(new ExtensionsDiff(null));
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("x-test", new Object());
        DiffContext context = Mockito.mock(DiffContext.class);

        // Mocking executeExtension to return Optional.of(true)
        doReturn(Optional.of(true)).when(extensionsDiff).executeExtension(anyString(), any());

        // When
        boolean result = extensionsDiff.isParentApplicable(Change.Type.ADDED, new Object(), extensions, context);

        // Then
        assertTrue(result);
    }
}
