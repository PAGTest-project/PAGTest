
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChangedOAuthFlow_isCoreChangedTest {

    private ChangedOAuthFlow changedOAuthFlow;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        context = mock(DiffContext.class);
        changedOAuthFlow = new ChangedOAuthFlow(null, null, context);
    }

    @Test
    public void testIsCoreChanged_NoChanges() {
        // Given
        changedOAuthFlow.setAuthorizationUrl(false).setTokenUrl(false).setRefreshUrl(false);

        // When
        DiffResult result = changedOAuthFlow.isCoreChanged();

        // Then
        assertEquals(DiffResult.NO_CHANGES, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible_AuthorizationUrl() {
        // Given
        changedOAuthFlow.setAuthorizationUrl(true).setTokenUrl(false).setRefreshUrl(false);
        when(SECURITY_SCHEME_OAUTH2_AUTH_URL_CHANGED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedOAuthFlow.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible_TokenUrl() {
        // Given
        changedOAuthFlow.setAuthorizationUrl(false).setTokenUrl(true).setRefreshUrl(false);
        when(SECURITY_SCHEME_OAUTH2_TOKEN_URL_CHANGED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedOAuthFlow.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible_RefreshUrl() {
        // Given
        changedOAuthFlow.setAuthorizationUrl(false).setTokenUrl(false).setRefreshUrl(true);
        when(SECURITY_SCHEME_OAUTH2_REFRESH_URL_CHANGED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedOAuthFlow.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        // Given
        changedOAuthFlow.setAuthorizationUrl(true).setTokenUrl(true).setRefreshUrl(true);
        when(SECURITY_SCHEME_OAUTH2_AUTH_URL_CHANGED.enabled(context)).thenReturn(false);
        when(SECURITY_SCHEME_OAUTH2_TOKEN_URL_CHANGED.enabled(context)).thenReturn(false);
        when(SECURITY_SCHEME_OAUTH2_REFRESH_URL_CHANGED.enabled(context)).thenReturn(false);

        // When
        DiffResult result = changedOAuthFlow.isCoreChanged();

        // Then
        assertEquals(DiffResult.COMPATIBLE, result);
    }
}
