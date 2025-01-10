
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.OAuthFlow;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOAuthFlow_toStringTest {

    @Test
    public void testToString() {
        OAuthFlow oldOAuthFlow = new OAuthFlow();
        OAuthFlow newOAuthFlow = new OAuthFlow();
        ChangedOAuthFlow changedOAuthFlow = new ChangedOAuthFlow(oldOAuthFlow, newOAuthFlow, null)
                .setAuthorizationUrl(true)
                .setTokenUrl(false)
                .setRefreshUrl(true)
                .setExtensions(new ChangedExtensions());

        String expected = "ChangedOAuthFlow(oldOAuthFlow=" + oldOAuthFlow
                + ", newOAuthFlow=" + newOAuthFlow
                + ", authorizationUrl=true"
                + ", tokenUrl=false"
                + ", refreshUrl=true"
                + ", extensions=" + changedOAuthFlow.getExtensions()
                + ")";

        assertEquals(expected, changedOAuthFlow.toString());
    }
}
