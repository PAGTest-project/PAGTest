package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class ChangedSecurityRequirements_addMissingTest {

  @Test
  public void testAddMissing_InitialNull() {
    // Given
    ChangedSecurityRequirements changedSecurityRequirements =
        new ChangedSecurityRequirements(null, null, null);
    SecurityRequirement securityRequirement = new SecurityRequirement();

    // When
    changedSecurityRequirements.addMissing(securityRequirement);

    // Then
    assertEquals(1, changedSecurityRequirements.getMissing().size());
    assertTrue(changedSecurityRequirements.getMissing().contains(securityRequirement));
  }

  @Test
  public void testAddMissing_InitialNotNull() {
    // Given
    ChangedSecurityRequirements changedSecurityRequirements =
        new ChangedSecurityRequirements(null, null, null);
    changedSecurityRequirements.setMissing(new ArrayList<>());
    SecurityRequirement securityRequirement = new SecurityRequirement();

    // When
    changedSecurityRequirements.addMissing(securityRequirement);

    // Then
    assertEquals(1, changedSecurityRequirements.getMissing().size());
    assertTrue(changedSecurityRequirements.getMissing().contains(securityRequirement));
  }
}
