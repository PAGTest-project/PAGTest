package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.security.OAuthFlows;
import org.junit.jupiter.api.Test;

class ChangedOAuthFlows_setClientCredentialOAuthFlowTest {

  @Test
  void testSetClientCredentialOAuthFlow() {
    // Given
    OAuthFlows oldOAuthFlows = new OAuthFlows();
    OAuthFlows newOAuthFlows = new OAuthFlows();
    ChangedOAuthFlows changedOAuthFlows = new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows);
    ChangedOAuthFlow clientCredentialOAuthFlow = new ChangedOAuthFlow(null, null, null);

    // When
    ChangedOAuthFlows result =
        changedOAuthFlows.setClientCredentialOAuthFlow(clientCredentialOAuthFlow);

    // Then
    assertEquals(clientCredentialOAuthFlow, result.getClientCredentialOAuthFlow());
  }
}
