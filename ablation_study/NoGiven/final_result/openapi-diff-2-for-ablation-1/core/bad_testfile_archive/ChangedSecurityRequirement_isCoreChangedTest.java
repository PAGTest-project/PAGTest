package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.SecurityRequirementDiff;

public class ChangedSecurityRequirement_isCoreChangedTest {

  private ChangedSecurityRequirement changedSecurityRequirement;
  private DiffContext context;

  @BeforeEach
  public void setUp() {
    context = mock(DiffContext.class);
    changedSecurityRequirement = new ChangedSecurityRequirement(null, null, context);
  }

  @Test
  public void testIsCoreChanged_NoChanges() {
    // Given
    changedSecurityRequirement.setMissing(null);
    changedSecurityRequirement.setIncreased(null);

    // When
    DiffResult result = changedSecurityRequirement.isCoreChanged();

    // Then
    assertEquals(DiffResult.NO_CHANGES, result);
  }

  @Test
  public void testIsCoreChanged_Increased_Incompatible() {
    // Given
    changedSecurityRequirement.addIncreased("key", List.of("scope"));
    when(SecurityRequirementDiff.SECURITY_REQUIREMENT_SCHEMES_INCREASED.enabled(context))
        .thenReturn(true);

    // When
    DiffResult result = changedSecurityRequirement.isCoreChanged();

    // Then
    assertEquals(DiffResult.INCOMPATIBLE, result);
  }

  @Test
  public void testIsCoreChanged_Increased_Compatible() {
    // Given
    changedSecurityRequirement.addIncreased("key", List.of("scope"));
    when(SecurityRequirementDiff.SECURITY_REQUIREMENT_SCHEMES_INCREASED.enabled(context))
        .thenReturn(false);

    // When
    DiffResult result = changedSecurityRequirement.isCoreChanged();

    // Then
    assertEquals(DiffResult.COMPATIBLE, result);
  }

  @Test
  public void testIsCoreChanged_Missing_Compatible() {
    // Given
    changedSecurityRequirement.addMissing("key", List.of("scope"));

    // When
    DiffResult result = changedSecurityRequirement.isCoreChanged();

    // Then
    assertEquals(DiffResult.COMPATIBLE, result);
  }
}
