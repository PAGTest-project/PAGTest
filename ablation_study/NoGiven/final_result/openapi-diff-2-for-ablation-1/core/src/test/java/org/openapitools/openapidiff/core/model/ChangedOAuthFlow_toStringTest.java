package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.security.OAuthFlow;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedOAuthFlow_toStringTest {

  @Test
  public void testToString() {
    OAuthFlow oldOAuthFlow = new OAuthFlow();
    OAuthFlow newOAuthFlow = new OAuthFlow();
    Map<String, Object> oldExtensions = new HashMap<>();
    Map<String, Object> newExtensions = new HashMap<>();
    ChangedExtensions changedExtensions = new ChangedExtensions(oldExtensions, newExtensions, null);
    ChangedOAuthFlow changedOAuthFlow =
        new ChangedOAuthFlow(oldOAuthFlow, newOAuthFlow, null)
            .setAuthorizationUrl(true)
            .setTokenUrl(false)
            .setRefreshUrl(true)
            .setExtensions(changedExtensions);

    String expected =
        "ChangedOAuthFlow(oldOAuthFlow="
            + oldOAuthFlow
            + ", newOAuthFlow="
            + newOAuthFlow
            + ", authorizationUrl=true"
            + ", tokenUrl=false"
            + ", refreshUrl=true"
            + ", extensions="
            + changedOAuthFlow.getExtensions()
            + ")";

    assertEquals(expected, changedOAuthFlow.toString());
  }
}
