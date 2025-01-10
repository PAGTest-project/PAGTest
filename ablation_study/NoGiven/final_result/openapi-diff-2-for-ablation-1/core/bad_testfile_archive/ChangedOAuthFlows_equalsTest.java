package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.security.OAuthFlows;
import org.junit.jupiter.api.Test;

public class ChangedOAuthFlows_equalsTest {

  @Test
  public void testEquals_SameInstance() {
    ChangedOAuthFlows changedOAuthFlows = new ChangedOAuthFlows(new OAuthFlows(), new OAuthFlows());
    assertTrue(changedOAuthFlows.equals(changedOAuthFlows));
  }

  @Test
  public void testEquals_DifferentClass() {
    ChangedOAuthFlows changedOAuthFlows = new ChangedOAuthFlows(new OAuthFlows(), new OAuthFlows());
    assertFalse(changedOAuthFlows.equals(new Object()));
  }

  @Test
  public void testEquals_Null() {
    ChangedOAuthFlows changedOAuthFlows = new ChangedOAuthFlows(new OAuthFlows(), new OAuthFlows());
    assertFalse(changedOAuthFlows.equals(null));
  }

  @Test
  public void testEquals_DifferentFields() {
    OAuthFlows oldOAuthFlows = new OAuthFlows();
    OAuthFlows newOAuthFlows = new OAuthFlows();
    ChangedOAuthFlows changedOAuthFlows1 =
        new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows)
            .setImplicitOAuthFlow(new ChangedOAuthFlow(null, null, null))
            .setPasswordOAuthFlow(new ChangedOAuthFlow(null, null, null))
            .setClientCredentialOAuthFlow(new ChangedOAuthFlow(null, null, null))
            .setAuthorizationCodeOAuthFlow(new ChangedOAuthFlow(null, null, null))
            .setExtensions(new ChangedExtensions(null, null, null));

    ChangedOAuthFlows changedOAuthFlows2 =
        new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows)
            .setImplicitOAuthFlow(new ChangedOAuthFlow(null, null, null))
            .setPasswordOAuthFlow(new ChangedOAuthFlow(null, null, null))
            .setClientCredentialOAuthFlow(new ChangedOAuthFlow(null, null, null))
            .setAuthorizationCodeOAuthFlow(new ChangedOAuthFlow(null, null, null))
            .setExtensions(new ChangedExtensions(null, null, null));

    assertTrue(changedOAuthFlows1.equals(changedOAuthFlows2));
  }

  @Test
  public void testEquals_DifferentOldOAuthFlows() {
    OAuthFlows oldOAuthFlows1 = new OAuthFlows();
    OAuthFlows oldOAuthFlows2 = new OAuthFlows();
    OAuthFlows newOAuthFlows = new OAuthFlows();
    ChangedOAuthFlows changedOAuthFlows1 = new ChangedOAuthFlows(oldOAuthFlows1, newOAuthFlows);
    ChangedOAuthFlows changedOAuthFlows2 = new ChangedOAuthFlows(oldOAuthFlows2, newOAuthFlows);

    assertFalse(changedOAuthFlows1.equals(changedOAuthFlows2));
  }

  @Test
  public void testEquals_DifferentNewOAuthFlows() {
    OAuthFlows oldOAuthFlows = new OAuthFlows();
    OAuthFlows newOAuthFlows1 = new OAuthFlows();
    OAuthFlows newOAuthFlows2 = new OAuthFlows();
    ChangedOAuthFlows changedOAuthFlows1 = new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows1);
    ChangedOAuthFlows changedOAuthFlows2 = new ChangedOAuthFlows(oldOAuthFlows, newOAuthFlows2);

    assertFalse(changedOAuthFlows1.equals(changedOAuthFlows2));
  }
}
