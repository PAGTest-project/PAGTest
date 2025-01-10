package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.BackwardIncompatibleProp;

public class ChangedPath_isCoreChangedTest {

  private ChangedPath changedPath;
  private DiffContext context;

  @BeforeEach
  public void setUp() {
    PathItem oldPath = new PathItem();
    PathItem newPath = new PathItem();
    context = mock(DiffContext.class);
    changedPath = new ChangedPath("/path", oldPath, newPath, context);
  }

  @Test
  public void testIsCoreChanged_NoChanges() {
    // Given
    Map<PathItem.HttpMethod, Operation> increased = new HashMap<>();
    Map<PathItem.HttpMethod, Operation> missing = new HashMap<>();
    changedPath.setIncreased(increased).setMissing(missing);

    // When
    DiffResult result = changedPath.isCoreChanged();

    // Then
    assertEquals(DiffResult.NO_CHANGES, result);
  }

  @Test
  public void testIsCoreChanged_Incompatible() {
    // Given
    Map<PathItem.HttpMethod, Operation> increased = new HashMap<>();
    Map<PathItem.HttpMethod, Operation> missing = new HashMap<>();
    missing.put(PathItem.HttpMethod.GET, new Operation());
    changedPath.setIncreased(increased).setMissing(missing);
    when(BackwardIncompatibleProp.OPENAPI_ENDPOINTS_DECREASED.enabled(context)).thenReturn(true);

    // When
    DiffResult result = changedPath.isCoreChanged();

    // Then
    assertEquals(DiffResult.INCOMPATIBLE, result);
  }

  @Test
  public void testIsCoreChanged_Compatible() {
    // Given
    Map<PathItem.HttpMethod, Operation> increased = new HashMap<>();
    Map<PathItem.HttpMethod, Operation> missing = new HashMap<>();
    missing.put(PathItem.HttpMethod.GET, new Operation());
    changedPath.setIncreased(increased).setMissing(missing);
    when(BackwardIncompatibleProp.OPENAPI_ENDPOINTS_DECREASED.enabled(context)).thenReturn(false);

    // When
    DiffResult result = changedPath.isCoreChanged();

    // Then
    assertEquals(DiffResult.COMPATIBLE, result);
  }
}
