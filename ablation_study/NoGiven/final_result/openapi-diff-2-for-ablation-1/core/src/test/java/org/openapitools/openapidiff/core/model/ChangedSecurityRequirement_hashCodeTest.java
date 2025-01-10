package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class ChangedSecurityRequirement_hashCodeTest {

  @Test
  public void testHashCode() {
    SecurityRequirement oldSecurityRequirement = new SecurityRequirement();
    SecurityRequirement newSecurityRequirement = new SecurityRequirement();
    SecurityRequirement missing = new SecurityRequirement();
    SecurityRequirement increased = new SecurityRequirement();
    ChangedSecurityScheme changedSecurityScheme = new ChangedSecurityScheme(null, null, null);
    ChangedSecurityRequirement changedSecurityRequirement =
        new ChangedSecurityRequirement(oldSecurityRequirement, newSecurityRequirement, null)
            .setMissing(missing)
            .setIncreased(increased);
    changedSecurityRequirement.addChanged(changedSecurityScheme);

    int expectedHashCode =
        Objects.hash(
            oldSecurityRequirement,
            newSecurityRequirement,
            missing,
            increased,
            changedSecurityRequirement.getChanged());
    assertEquals(expectedHashCode, changedSecurityRequirement.hashCode());
  }
}
