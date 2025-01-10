package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedExtensions_getChangedElementsTest {

  @Test
  public void testGetChangedElements() {
    // Given
    Map<String, Object> oldExtensions = new HashMap<>();
    Map<String, Object> newExtensions = new HashMap<>();
    DiffContext context = new DiffContext(new OpenApiDiffOptions());
    ChangedExtensions changedExtensions =
        new ChangedExtensions(oldExtensions, newExtensions, context);

    Map<String, Changed> increased = new HashMap<>();
    increased.put("key1", new ChangedImpl());
    increased.put("key2", new ChangedImpl());

    Map<String, Changed> missing = new HashMap<>();
    missing.put("key3", new ChangedImpl());

    Map<String, Changed> changed = new HashMap<>();
    changed.put("key4", new ChangedImpl());

    changedExtensions.setIncreased(increased);
    changedExtensions.setMissing(missing);
    changedExtensions.setChanged(changed);

    // When
    List<Changed> result = changedExtensions.getChangedElements();

    // Then
    assertEquals(4, result.size());
  }

  private static class ChangedImpl implements Changed {
    @Override
    public DiffResult isChanged() {
      return DiffResult.NO_CHANGES;
    }
  }
}
