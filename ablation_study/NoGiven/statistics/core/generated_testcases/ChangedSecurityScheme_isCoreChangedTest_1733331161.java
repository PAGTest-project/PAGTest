
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.SecuritySchemeDiff;

public class ChangedSecurityScheme_isCoreChangedTest {

    @Test
    public void testIsCoreChanged_NoChanges() {
        DiffContext context = mock(DiffContext.class);
        ChangedSecuritySchemeScopes changedScopes = mock(ChangedSecuritySchemeScopes.class);
        when(changedScopes.isUnchanged()).thenReturn(true);

        ChangedSecurityScheme changedSecurityScheme = new ChangedSecurityScheme(null, null, context)
            .setChangedType(false)
            .setChangedIn(false)
            .setChangedScheme(false)
            .setChangedBearerFormat(false)
            .setChangedOpenIdConnectUrl(false)
            .setChangedScopes(changedScopes);

        assertEquals(DiffResult.NO_CHANGES, changedSecurityScheme.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_IncompatibleChanges() {
        DiffContext context = mock(DiffContext.class);
        when(SecuritySchemeDiff.SECURITY_SCHEME_BEARER_FORMAT_CHANGED.enabled(context)).thenReturn(true);
        when(SecuritySchemeDiff.SECURITY_SCHEME_OPENIDCONNECT_URL_CHANGED.enabled(context)).thenReturn(true);
        when(SecuritySchemeDiff.SECURITY_SCHEME_SCHEME_CHANGED.enabled(context)).thenReturn(true);
        when(SecuritySchemeDiff.SECURITY_SCHEME_SCOPES_INCREASED.enabled(context)).thenReturn(true);

        ChangedSecuritySchemeScopes changedScopes = mock(ChangedSecuritySchemeScopes.class);
        when(changedScopes.isUnchanged()).thenReturn(false);
        when(changedScopes.getIncreased()).thenReturn(Arrays.asList("scope1"));

        ChangedSecurityScheme changedSecurityScheme = new ChangedSecurityScheme(null, null, context)
            .setChangedType(true)
            .setChangedIn(true)
            .setChangedScheme(true)
            .setChangedBearerFormat(true)
            .setChangedOpenIdConnectUrl(true)
            .setChangedScopes(changedScopes);

        assertEquals(DiffResult.INCOMPATIBLE, changedSecurityScheme.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_CompatibleChanges() {
        DiffContext context = mock(DiffContext.class);
        when(SecuritySchemeDiff.SECURITY_SCHEME_BEARER_FORMAT_CHANGED.enabled(context)).thenReturn(false);
        when(SecuritySchemeDiff.SECURITY_SCHEME_OPENIDCONNECT_URL_CHANGED.enabled(context)).thenReturn(false);
        when(SecuritySchemeDiff.SECURITY_SCHEME_SCHEME_CHANGED.enabled(context)).thenReturn(false);
        when(SecuritySchemeDiff.SECURITY_SCHEME_SCOPES_INCREASED.enabled(context)).thenReturn(false);

        ChangedSecuritySchemeScopes changedScopes = mock(ChangedSecuritySchemeScopes.class);
        when(changedScopes.isUnchanged()).thenReturn(true);
        when(changedScopes.getIncreased()).thenReturn(Collections.emptyList());

        ChangedSecurityScheme changedSecurityScheme = new ChangedSecurityScheme(null, null, context)
            .setChangedType(false)
            .setChangedIn(false)
            .setChangedScheme(true)
            .setChangedBearerFormat(true)
            .setChangedOpenIdConnectUrl(true)
            .setChangedScopes(changedScopes);

        assertEquals(DiffResult.COMPATIBLE, changedSecurityScheme.isCoreChanged());
    }
}
