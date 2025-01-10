
package org.openapitools.openapidiff.core.compare;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ExtensionDiff;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ExtensionsDiff_executeExtensionTest {

    private ExtensionsDiff extensionsDiff;
    private OpenApiDiff openApiDiff;
    private ExtensionDiff mockExtensionDiff;

    @BeforeEach
    public void setUp() {
        openApiDiff = Mockito.mock(OpenApiDiff.class);
        extensionsDiff = new ExtensionsDiff(openApiDiff);
        mockExtensionDiff = Mockito.mock(ExtensionDiff.class);
    }

    @Test
    public void testExecuteExtension_Success() {
        // Given
        String name = "x-test";
        Function<ExtensionDiff, Boolean> predicate = ExtensionDiff::isParentApplicable;
        when(extensionsDiff.getExtensionDiff(name)).thenReturn(Optional.of(mockExtensionDiff));
        when(mockExtensionDiff.setOpenApiDiff(openApiDiff)).thenReturn(mockExtensionDiff);
        when(predicate.apply(mockExtensionDiff)).thenReturn(true);

        // When
        Optional<Boolean> result = extensionsDiff.executeExtension(name, predicate);

        // Then
        assertTrue(result.isPresent());
        assertTrue(result.get());
    }
}
