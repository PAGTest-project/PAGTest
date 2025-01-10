
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.OAuthFlows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOAuthFlows_hashCodeTest {

    @Test
    public void testHashCode() {
        OAuthFlows oldOAuthFlows = new OAuthFlows();
        OAuthFlows newOAuthFlows = new OAuthFlows();
        ChangedOAuthFlows changedOAuthFlows = new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows);

        ChangedOAuthFlow implicitOAuthFlow = new ChangedOAuthFlow(null, null, null);
        ChangedOAuthFlow passwordOAuthFlow = new ChangedOAuthFlow(null, null, null);
        ChangedOAuthFlow clientCredentialOAuthFlow = new ChangedOAuthFlow(null, null, null);
        ChangedOAuthFlow authorizationCodeOAuthFlow = new ChangedOAuthFlow(null, null, null);
        ChangedExtensions extensions = new ChangedExtensions(null, null, null);

        changedOAuthFlows.setImplicitOAuthFlow(implicitOAuthFlow);
        changedOAuthFlows.setPasswordOAuthFlow(passwordOAuthFlow);
        changedOAuthFlows.setClientCredentialOAuthFlow(clientCredentialOAuthFlow);
        changedOAuthFlows.setAuthorizationCodeOAuthFlow(authorizationCodeOAuthFlow);
        changedOAuthFlows.setExtensions(extensions);

        int expectedHashCode = Objects.hash(
            oldOAuthFlows,
            newOAuthFlows,
            implicitOAuthFlow,
            passwordOAuthFlow,
            clientCredentialOAuthFlow,
            authorizationCodeOAuthFlow,
            extensions
        );

        assertEquals(expectedHashCode, changedOAuthFlows.hashCode());
    }
}
