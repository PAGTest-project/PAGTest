
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOAuthFlows_toStringTest {

    @Test
    public void testToString() {
        OAuthFlows oldOAuthFlows = new OAuthFlows();
        OAuthFlows newOAuthFlows = new OAuthFlows();
        ChangedOAuthFlows changedOAuthFlows = new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows);

        OAuthFlow oldImplicitFlow = new OAuthFlow();
        OAuthFlow newImplicitFlow = new OAuthFlow();
        ChangedOAuthFlow implicitOAuthFlow = new ChangedOAuthFlow(oldImplicitFlow, newImplicitFlow, null);

        OAuthFlow oldPasswordFlow = new OAuthFlow();
        OAuthFlow newPasswordFlow = new OAuthFlow();
        ChangedOAuthFlow passwordOAuthFlow = new ChangedOAuthFlow(oldPasswordFlow, newPasswordFlow, null);

        OAuthFlow oldClientCredentialFlow = new OAuthFlow();
        OAuthFlow newClientCredentialFlow = new OAuthFlow();
        ChangedOAuthFlow clientCredentialOAuthFlow = new ChangedOAuthFlow(oldClientCredentialFlow, newClientCredentialFlow, null);

        OAuthFlow oldAuthorizationCodeFlow = new OAuthFlow();
        OAuthFlow newAuthorizationCodeFlow = new OAuthFlow();
        ChangedOAuthFlow authorizationCodeOAuthFlow = new ChangedOAuthFlow(oldAuthorizationCodeFlow, newAuthorizationCodeFlow, null);

        ChangedExtensions extensions = new ChangedExtensions(null, null, null);

        changedOAuthFlows.setImplicitOAuthFlow(implicitOAuthFlow);
        changedOAuthFlows.setPasswordOAuthFlow(passwordOAuthFlow);
        changedOAuthFlows.setClientCredentialOAuthFlow(clientCredentialOAuthFlow);
        changedOAuthFlows.setAuthorizationCodeOAuthFlow(authorizationCodeOAuthFlow);
        changedOAuthFlows.setExtensions(extensions);

        String expected = "ChangedOAuthFlows(oldOAuthFlows="
                + oldOAuthFlows
                + ", newOAuthFlows="
                + newOAuthFlows
                + ", implicitOAuthFlow="
                + implicitOAuthFlow
                + ", passwordOAuthFlow="
                + passwordOAuthFlow
                + ", clientCredentialOAuthFlow="
                + clientCredentialOAuthFlow
                + ", authorizationCodeOAuthFlow="
                + authorizationCodeOAuthFlow
                + ", extensions="
                + extensions
                + ")";

        assertEquals(expected, changedOAuthFlows.toString());
    }
}
