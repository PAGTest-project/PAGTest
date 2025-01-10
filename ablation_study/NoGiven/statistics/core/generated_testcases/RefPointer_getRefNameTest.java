
package org.openapitools.openapidiff.core.utils;

import io.swagger.v3.oas.models.Components;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RefPointer_getRefNameTest {

    @Test
    void testGetRefName_NullRef() {
        RefPointer<Object> refPointer = new RefPointer<>(RefType.SCHEMAS);
        assertNull(refPointer.getRefName(null));
    }

    @Test
    void testGetRefName_SecuritySchemes() {
        RefPointer<Object> refPointer = new RefPointer<>(RefType.SECURITY_SCHEMES);
        assertEquals("securityRef", refPointer.getRefName("securityRef"));
    }

    @Test
    void testGetRefName_ValidRef() {
        RefPointer<Object> refPointer = new RefPointer<>(RefType.SCHEMAS);
        assertEquals("schemaRef", refPointer.getRefName("#/components/schemas/schemaRef"));
    }

    @Test
    void testGetRefName_InvalidRef() {
        RefPointer<Object> refPointer = new RefPointer<>(RefType.SCHEMAS);
        assertThrows(IllegalArgumentException.class, () -> refPointer.getRefName("invalidRef"));
    }
}
