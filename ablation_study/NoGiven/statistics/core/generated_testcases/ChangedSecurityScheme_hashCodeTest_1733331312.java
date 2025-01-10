
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedSecurityScheme_hashCodeTest {

    @Test
    public void testHashCode() {
        SecurityScheme oldScheme = new SecurityScheme();
        SecurityScheme newScheme = new SecurityScheme();
        DiffContext context = new DiffContext();

        ChangedSecurityScheme changedSecurityScheme = new ChangedSecurityScheme(oldScheme, newScheme, context);
        changedSecurityScheme.setChangedType(true);
        changedSecurityScheme.setChangedIn(false);
        changedSecurityScheme.setChangedScheme(true);
        changedSecurityScheme.setChangedBearerFormat(false);
        changedSecurityScheme.setChangedOpenIdConnectUrl(true);
        changedSecurityScheme.setChangedScopes(null);
        changedSecurityScheme.setDescription(null);
        changedSecurityScheme.setOAuthFlows(null);
        changedSecurityScheme.setExtensions(null);

        int expectedHashCode = Objects.hash(
            oldScheme,
            newScheme,
            true,
            false,
            true,
            false,
            true,
            null,
            null,
            null,
            null
        );

        assertEquals(expectedHashCode, changedSecurityScheme.hashCode());
    }
}
