
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.OAuthFlows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOAuthFlows_toStringTest {

    @Test
    public void testToString() {
        OAuthFlows oldOAuthFlows = new OAuthFlows();
        OAuthFlows newOAuthFlows = new OAuthFlows();
        ChangedOAuthFlows changedOAuthFlows = new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows);

        ChangedOAuthFlow implicitOAuthFlow = new ChangedOAuthFlow();
        ChangedOAuthFlow passwordOAuthFlow = new ChangedOAuthFlow();
        ChangedOAuthFlow clientCredentialOAuthFlow = new ChangedOAuthFlow();
        ChangedOAuthFlow authorizationCodeOAuthFlow = new ChangedOAuthFlow();
        ChangedExtensions extensions = new ChangedExtensions();

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
