
package org.openapitools.openapidiff.core.utils;

import io.swagger.v3.oas.models.Components;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RefPointer_resolveRefTest {

    @Test
    void testResolveRef_WithValidRef() {
        // Given
        Components components = mock(Components.class);
        RefPointer<String> refPointer = new RefPointer<>(RefType.SCHEMAS);
        String ref = "#/components/schemas/SchemaName";
        String expectedResult = "SchemaValue";

        when(components.getSchemas()).thenReturn(Map.of("SchemaName", expectedResult));

        // When
        String result = refPointer.resolveRef(components, "DefaultValue", ref);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    void testResolveRef_WithNullRef() {
        // Given
        Components components = mock(Components.class);
        RefPointer<String> refPointer = new RefPointer<>(RefType.SCHEMAS);
        String defaultValue = "DefaultValue";

        // When
        String result = refPointer.resolveRef(components, defaultValue, null);

        // Then
        assertEquals(defaultValue, result);
    }

    @Test
    void testResolveRef_WithNonExistentRef() {
        // Given
        Components components = mock(Components.class);
        RefPointer<String> refPointer = new RefPointer<>(RefType.SCHEMAS);
        String ref = "#/components/schemas/NonExistentSchema";

        when(components.getSchemas()).thenReturn(Map.of());

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            refPointer.resolveRef(components, "DefaultValue", ref);
        });

        assertEquals("ref 'NonExistentSchema' doesn't exist.", exception.getMessage());
    }
}
