
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedSecurityScheme;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.openapitools.openapidiff.core.model.deferred.RealizedChanged;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SecuritySchemeDiff_diffTest {

    private SecuritySchemeDiff securitySchemeDiff;
    private OpenApiDiff openApiDiff;
    private Components leftComponents;
    private Components rightComponents;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        openApiDiff = Mockito.mock(OpenApiDiff.class);
        leftComponents = Mockito.mock(Components.class);
        rightComponents = Mockito.mock(Components.class);
        context = Mockito.mock(DiffContext.class);

        when(openApiDiff.getOldSpecOpenApi()).thenReturn(null);
        when(openApiDiff.getNewSpecOpenApi()).thenReturn(null);

        securitySchemeDiff = new SecuritySchemeDiff(openApiDiff);
    }

    @Test
    public void testDiff_OAuth2Type_ScopesChanged() {
        String leftSchemeRef = "leftSchemeRef";
        String rightSchemeRef = "rightSchemeRef";
        List<String> leftScopes = Collections.singletonList("scope1");
        List<String> rightScopes = Collections.singletonList("scope2");

        SecurityScheme leftSecurityScheme = new SecurityScheme();
        leftSecurityScheme.setType(SecurityScheme.Type.OAUTH2);
        SecurityScheme rightSecurityScheme = new SecurityScheme();
        rightSecurityScheme.setType(SecurityScheme.Type.OAUTH2);

        when(leftComponents.getSecuritySchemes()).thenReturn(Collections.singletonMap(leftSchemeRef, leftSecurityScheme));
        when(rightComponents.getSecuritySchemes()).thenReturn(Collections.singletonMap(rightSchemeRef, rightSecurityScheme));

        DeferredChanged<ChangedSecurityScheme> changedSecuritySchemeOpt = new RealizedChanged<>(new ChangedSecurityScheme(leftSecurityScheme, rightSecurityScheme, context));
        when(securitySchemeDiff.cachedDiff(Mockito.anySet(), Mockito.any(), Mockito.any(), Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(changedSecuritySchemeOpt);

        DeferredChanged<ChangedSecurityScheme> result = securitySchemeDiff.diff(leftSchemeRef, leftScopes, rightSchemeRef, rightScopes, context);

        assertNotNull(result);
    }
}
