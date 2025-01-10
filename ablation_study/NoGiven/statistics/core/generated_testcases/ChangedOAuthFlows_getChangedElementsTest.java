
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.OAuthFlows;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOAuthFlows_getChangedElementsTest {

    @Test
    public void testGetChangedElements() {
        // Given
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

        // When
        List<Changed> changedElements = changedOAuthFlows.getChangedElements();

        // Then
        assertEquals(5, changedElements.size());
        assertEquals(implicitOAuthFlow, changedElements.get(0));
        assertEquals(passwordOAuthFlow, changedElements.get(1));
        assertEquals(clientCredentialOAuthFlow, changedElements.get(2));
        assertEquals(authorizationCodeOAuthFlow, changedElements.get(3));
        assertEquals(extensions, changedElements.get(4));
    }
}
