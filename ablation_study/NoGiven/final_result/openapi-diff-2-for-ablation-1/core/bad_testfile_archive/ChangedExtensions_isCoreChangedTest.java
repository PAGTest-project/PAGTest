package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedExtensions.BackwardIncompatibleProp;

public class ChangedExtensions_isCoreChangedTest {

  private ChangedExtensions changedExtensions;
  private DiffContext context;

  @BeforeEach
  public void setUp() {
    Map<String, Object> oldExtensions = new HashMap<>();
    Map<String, Object> newExtensions = new HashMap<>();
    context = mock(DiffContext.class);
    changedExtensions = new ChangedExtensions(oldExtensions, newExtensions, context);
  }

  @Test
  public void testIsCoreChanged_NoChanges() {
    // Given
    Map<String, Changed> increased = new HashMap<>();
    Map<String, Changed> missing = new HashMap<>();
    changedExtensions.setIncreased(increased);
    changedExtensions.setMissing(missing);

    // When
    DiffResult result = changedExtensions.isCoreChanged();

    // Then
    assertEquals(DiffResult.NO_CHANGES, result);
  }

  @Test
  public void testIsCoreChanged_IncompatibleDueToMissing() {
    // Given
    Map<String, Changed> increased = new HashMap<>();
    Map<String, Changed> missing = new HashMap<>();
    missing.put("key1", mock(Changed.class));
    changedExtensions.setIncreased(increased);
    changedExtensions.setMissing(missing);

    when(BackwardIncompatibleProp.EXTENSION_CONTENT_TYPES_DECREASED.enabled(context))
        .thenReturn(true);

    // When
    DiffResult result = changedExtensions.isCoreChanged();

    // Then
    assertEquals(DiffResult.INCOMPATIBLE, result);
  }

  @Test
  public void testIsCoreChanged_Compatible() {
    // Given
    Map<String, Changed> increased = new HashMap<>();
    Map<String, Changed> missing = new HashMap<>();
    missing.put("key1", mock(Changed.class));
    changedExtensions.setIncreased(increased);
    changedExtensions.setMissing(missing);

    when(BackwardIncompatibleProp.EXTENSION_CONTENT_TYPES_DECREASED.enabled(context))
        .thenReturn(false);
    when(BackwardIncompatibleProp.EXTENSION_CONTENT_TYPE_DELETED.enabled(context, "key1"))
        .thenReturn(false);

    // When
    DiffResult result = changedExtensions.isCoreChanged();

    // Then
    assertEquals(DiffResult.COMPATIBLE, result);
  }

  @Test
  public void testIsCoreChanged_IncompatibleDueToDeleted() {
    // Given
    Map<String, Changed> increased = new HashMap<>();
    Map<String, Changed> missing = new HashMap<>();
    missing.put("key1", mock(Changed.class));
    changedExtensions.setIncreased(increased);
    changedExtensions.setMissing(missing);

    when(BackwardIncompatibleProp.EXTENSION_CONTENT_TYPES_DECREASED.enabled(context))
        .thenReturn(false);
    when(BackwardIncompatibleProp.EXTENSION_CONTENT_TYPE_DELETED.enabled(context, "key1"))
        .thenReturn(true);

    // When
    DiffResult result = changedExtensions.isCoreChanged();

    // Then
    assertEquals(DiffResult.INCOMPATIBLE, result);
  }
}
