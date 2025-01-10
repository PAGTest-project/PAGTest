package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ChangedSecurityRequirements_addChangedTest {

  @Test
  public void testAddChanged_InitialNullList() {
    // Given
    ChangedSecurityRequirements changedSecurityRequirements =
        new ChangedSecurityRequirements(new ArrayList<>(), new ArrayList<>(), null);
    ChangedSecurityRequirement changedSecurityRequirement =
        new ChangedSecurityRequirement(new SecurityRequirement(), new SecurityRequirement(), null);

    // When
    changedSecurityRequirements.addChanged(changedSecurityRequirement);

    // Then
    List<ChangedSecurityRequirement> changedList = changedSecurityRequirements.getChanged();
    assertNotNull(changedList);
    assertEquals(1, changedList.size());
    assertEquals(changedSecurityRequirement, changedList.get(0));
  }

  @Test
  public void testAddChanged_ExistingList() {
    // Given
    ChangedSecurityRequirements changedSecurityRequirements =
        new ChangedSecurityRequirements(new ArrayList<>(), new ArrayList<>(), null);
    changedSecurityRequirements.setChanged(new ArrayList<>());
    ChangedSecurityRequirement changedSecurityRequirement =
        new ChangedSecurityRequirement(new SecurityRequirement(), new SecurityRequirement(), null);

    // When
    changedSecurityRequirements.addChanged(changedSecurityRequirement);

    // Then
    List<ChangedSecurityRequirement> changedList = changedSecurityRequirements.getChanged();
    assertNotNull(changedList);
    assertEquals(1, changedList.size());
    assertEquals(changedSecurityRequirement, changedList.get(0));
  }
}
