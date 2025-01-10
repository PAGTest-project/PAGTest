
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangedOAuthFlows_setAuthorizationCodeOAuthFlowTest {

    @Test
    public void testSetAuthorizationCodeOAuthFlow() {
        // Given
        OAuthFlows oldOAuthFlows = new OAuthFlows();
        OAuthFlows newOAuthFlows = new OAuthFlows();
        ChangedOAuthFlows changedOAuthFlows = new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows);
        ChangedOAuthFlow authorizationCodeOAuthFlow = new ChangedOAuthFlow(new OAuthFlow(), new OAuthFlow(), null);

        // When
        ChangedOAuthFlows result = changedOAuthFlows.setAuthorizationCodeOAuthFlow(authorizationCodeOAuthFlow);

        // Then
        assertSame(authorizationCodeOAuthFlow, result.getAuthorizationCodeOAuthFlow());
        assertSame(changedOAuthFlows, result);
    }
}
