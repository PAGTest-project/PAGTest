
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.media.Schema;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangedOneOfSchema_isCoreChangedTest {

  private ChangedOneOfSchema changedOneOfSchema;
  private DiffContext context;

  @BeforeEach
  public void setUp() {
    context = mock(DiffContext.class);
    changedOneOfSchema = new ChangedOneOfSchema(new HashMap<>(), new HashMap<>(), context);
  }

  @Test
  public void testIsCoreChanged_NoChanges() {
    changedOneOfSchema.setIncreased(new HashMap<>()).setMissing(new HashMap<>());
    assertEquals(DiffResult.NO_CHANGES, changedOneOfSchema.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_IncompatibleRequest() {
    Map<String, Schema> missing = new HashMap<>();
    missing.put("key", new Schema());
    changedOneOfSchema.setIncreased(new HashMap<>()).setMissing(missing);
    when(context.isRequest()).thenReturn(true);
    when(REQUEST_ONEOF_DECREASED.enabled(context)).thenReturn(true);
    assertEquals(DiffResult.INCOMPATIBLE, changedOneOfSchema.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_IncompatibleResponse() {
    Map<String, Schema> increased = new HashMap<>();
    increased.put("key", new Schema());
    changedOneOfSchema.setIncreased(increased).setMissing(new HashMap<>());
    when(context.isResponse()).thenReturn(true);
    when(RESPONSE_ONEOF_INCREASED.enabled(context)).thenReturn(true);
    assertEquals(DiffResult.INCOMPATIBLE, changedOneOfSchema.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_Compatible() {
    Map<String, Schema> increased = new HashMap<>();
    increased.put("key", new Schema());
    changedOneOfSchema.setIncreased(increased).setMissing(new HashMap<>());
    when(context.isResponse()).thenReturn(true);
    when(RESPONSE_ONEOF_INCREASED.enabled(context)).thenReturn(false);
    assertEquals(DiffResult.COMPATIBLE, changedOneOfSchema.isCoreChanged());
  }
}
