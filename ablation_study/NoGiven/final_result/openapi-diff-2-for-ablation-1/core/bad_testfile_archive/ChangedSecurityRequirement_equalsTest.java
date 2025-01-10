package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;

class ChangedSecurityRequirement_equalsTest {

  @Test
  void testEquals_SameObject() {
    ChangedSecurityRequirement req = new ChangedSecurityRequirement(null, null, null);
    assertTrue(req.equals(req));
  }

  @Test
  void testEquals_NullObject() {
    ChangedSecurityRequirement req = new ChangedSecurityRequirement(null, null, null);
    assertFalse(req.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    ChangedSecurityRequirement req = new ChangedSecurityRequirement(null, null, null);
    assertFalse(req.equals(new Object()));
  }

  @Test
  void testEquals_DifferentFields() {
    SecurityRequirement oldReq = new SecurityRequirement();
    SecurityRequirement newReq = new SecurityRequirement();
    SecurityRequirement missingReq = new SecurityRequirement();
    SecurityRequirement increasedReq = new SecurityRequirement();
    ChangedSecurityRequirement req1 =
        new ChangedSecurityRequirement(oldReq, newReq, null)
            .setMissing(missingReq)
            .setIncreased(increasedReq);
    ChangedSecurityRequirement req2 =
        new ChangedSecurityRequirement(oldReq, newReq, null)
            .setMissing(missingReq)
            .setIncreased(increasedReq);
    assertTrue(req1.equals(req2));

    req2.setIncreased(new SecurityRequirement());
    assertFalse(req1.equals(req2));
  }
}
