package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.media.MediaType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangedContent_isCoreChangedTest {

  private ChangedContent changedContent;
  private DiffContext context;

  @BeforeEach
  public void setUp() {
    context = mock(DiffContext.class);
    changedContent = new ChangedContent(null, null, context);
  }

  @Test
  public void testIsCoreChanged_NoChanges() {
    changedContent.setIncreased(new HashMap<>()).setMissing(new HashMap<>());
    assertEquals(DiffResult.NO_CHANGES, changedContent.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_IncompatibleRequest() {
    Map<String, MediaType> missing = new HashMap<>();
    missing.put("key", new MediaType());
    changedContent.setIncreased(new HashMap<>()).setMissing(missing);
    when(context.isRequest()).thenReturn(true);
    when(BackwardIncompatibleProp.REQUEST_CONTENT_DECREASED.enabled(context)).thenReturn(true);
    assertEquals(DiffResult.INCOMPATIBLE, changedContent.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_IncompatibleResponse() {
    Map<String, MediaType> missing = new HashMap<>();
    missing.put("key", new MediaType());
    changedContent.setIncreased(new HashMap<>()).setMissing(missing);
    when(context.isResponse()).thenReturn(true);
    when(BackwardIncompatibleProp.RESPONSE_CONTENT_DECREASED.enabled(context)).thenReturn(true);
    assertEquals(DiffResult.INCOMPATIBLE, changedContent.isCoreChanged());
  }

  @Test
  public void testIsCoreChanged_Compatible() {
    Map<String, MediaType> missing = new HashMap<>();
    missing.put("key", new MediaType());
    changedContent.setIncreased(new HashMap<>()).setMissing(missing);
    when(context.isRequest()).thenReturn(true);
    when(BackwardIncompatibleProp.REQUEST_CONTENT_DECREASED.enabled(context)).thenReturn(false);
    assertEquals(DiffResult.COMPATIBLE, changedContent.isCoreChanged());
  }
}
