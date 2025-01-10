package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangedSchema_isChangedTest {

  private ChangedSchema changedSchema;
  private DiffResult mockCoreChangedResult;
  private List<Changed> mockChangedElements;

  @BeforeEach
  public void setUp() {
    changedSchema = new ChangedSchema();
    mockCoreChangedResult = mock(DiffResult.class);
    mockChangedElements = Collections.singletonList(mock(Changed.class));
  }

  @Test
  public void testIsChanged_NoRecursiveCall() {
    // Given
    changedSchema.clearChangedCache();
    changedSchema.setCoreChanged(mockCoreChangedResult);
    when(mockCoreChangedResult.getWeight()).thenReturn(1);

    Changed mockChanged = mock(Changed.class);
    when(mockChanged.isChanged()).thenReturn(DiffResult.NO_CHANGES);
    mockChangedElements = Collections.singletonList(mockChanged);

    // When
    DiffResult result = changedSchema.isChanged();

    // Then
    assertEquals(mockCoreChangedResult, result);
  }

  @Test
  public void testIsChanged_RecursiveCall() {
    // Given
    changedSchema.clearChangedCache();
    changedSchema.setCoreChanged(mockCoreChangedResult);
    when(mockCoreChangedResult.getWeight()).thenReturn(0);

    Changed mockChanged = mock(Changed.class);
    when(mockChanged.isChanged()).thenReturn(DiffResult.COMPATIBLE);
    mockChangedElements = Collections.singletonList(mockChanged);

    // When
    DiffResult result = changedSchema.isChanged();

    // Then
    assertEquals(DiffResult.COMPATIBLE, result);
  }

  @Test
  public void testIsChanged_CachedResult() {
    // Given
    changedSchema.setChanged(DiffResult.NO_CHANGES);

    // When
    DiffResult result = changedSchema.isChanged();

    // Then
    assertEquals(DiffResult.NO_CHANGES, result);
  }
}
