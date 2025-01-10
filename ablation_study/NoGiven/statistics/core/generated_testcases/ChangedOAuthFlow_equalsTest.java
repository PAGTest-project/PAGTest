
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.security.OAuthFlow;
import org.junit.jupiter.api.Test;

public class ChangedOAuthFlow_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ChangedOAuthFlow flow = new ChangedOAuthFlow(new OAuthFlow(), new OAuthFlow(), null);
        assertTrue(flow.equals(flow));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedOAuthFlow flow = new ChangedOAuthFlow(new OAuthFlow(), new OAuthFlow(), null);
        assertFalse(flow.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedOAuthFlow flow = new ChangedOAuthFlow(new OAuthFlow(), new OAuthFlow(), null);
        assertFalse(flow.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentFields() {
        OAuthFlow oldFlow = new OAuthFlow();
        OAuthFlow newFlow = new OAuthFlow();
        ChangedOAuthFlow flow1 = new ChangedOAuthFlow(oldFlow, newFlow, null)
                .setAuthorizationUrl(true)
                .setTokenUrl(true)
                .setRefreshUrl(true)
                .setExtensions(new ChangedExtensions(null, null, null));

        ChangedOAuthFlow flow2 = new ChangedOAuthFlow(oldFlow, newFlow, null)
                .setAuthorizationUrl(false)
                .setTokenUrl(false)
                .setRefreshUrl(false)
                .setExtensions(null);

        assertFalse(flow1.equals(flow2));
    }

    @Test
    public void testEquals_SameFields() {
        OAuthFlow oldFlow = new OAuthFlow();
        OAuthFlow newFlow = new OAuthFlow();
        ChangedOAuthFlow flow1 = new ChangedOAuthFlow(oldFlow, newFlow, null)
                .setAuthorizationUrl(true)
                .setTokenUrl(true)
                .setRefreshUrl(true)
                .setExtensions(new ChangedExtensions(null, null, null));

        ChangedOAuthFlow flow2 = new ChangedOAuthFlow(oldFlow, newFlow, null)
                .setAuthorizationUrl(true)
                .setTokenUrl(true)
                .setRefreshUrl(true)
                .setExtensions(new ChangedExtensions(null, null, null));

        assertTrue(flow1.equals(flow2));
    }
}
