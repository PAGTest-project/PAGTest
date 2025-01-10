
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityDiffInfo_hashCodeTest {

    @Test
    public void testHashCodeWithAllNullFields() {
        SecurityDiffInfo securityDiffInfo = new SecurityDiffInfo(null, null, null);
        assertEquals(0, securityDiffInfo.hashCode());
    }

    @Test
    public void testHashCodeWithNonNullSecurityScheme() {
        SecurityScheme securityScheme = new SecurityScheme();
        SecurityDiffInfo securityDiffInfo = new SecurityDiffInfo(null, securityScheme, null);
        assertEquals(31 * securityScheme.hashCode(), securityDiffInfo.hashCode());
    }

    @Test
    public void testHashCodeWithNonNullScopes() {
        List<String> scopes = Arrays.asList("read", "write");
        SecurityDiffInfo securityDiffInfo = new SecurityDiffInfo(null, null, scopes);
        int expectedHashCode = 31 * scopes.hashCode();
        assertEquals(expectedHashCode, securityDiffInfo.hashCode());
    }

    @Test
    public void testHashCodeWithBothNonNullFields() {
        SecurityScheme securityScheme = new SecurityScheme();
        List<String> scopes = Arrays.asList("read", "write");
        SecurityDiffInfo securityDiffInfo = new SecurityDiffInfo(null, securityScheme, scopes);
        int expectedHashCode = 31 * securityScheme.hashCode() + scopes.hashCode();
        assertEquals(expectedHashCode, securityDiffInfo.hashCode());
    }
}
