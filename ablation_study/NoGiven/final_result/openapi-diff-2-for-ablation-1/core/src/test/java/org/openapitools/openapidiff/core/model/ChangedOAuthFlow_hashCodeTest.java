package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.security.OAuthFlow;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class ChangedOAuthFlow_hashCodeTest {

  @Test
  public void testHashCode() {
    // Given
    OAuthFlow oldOAuthFlow = new OAuthFlow();
    OAuthFlow newOAuthFlow = new OAuthFlow();
    ChangedExtensions extensions = new ChangedExtensions(null, null, null);
    ChangedOAuthFlow changedOAuthFlow =
        new ChangedOAuthFlow(oldOAuthFlow, newOAuthFlow, null)
            .setAuthorizationUrl(true)
            .setTokenUrl(false)
            .setRefreshUrl(true)
            .setExtensions(extensions);

    // When
    int hashCode = changedOAuthFlow.hashCode();

    // Then
    assertEquals(Objects.hash(oldOAuthFlow, newOAuthFlow, true, false, true, extensions), hashCode);
  }
}
