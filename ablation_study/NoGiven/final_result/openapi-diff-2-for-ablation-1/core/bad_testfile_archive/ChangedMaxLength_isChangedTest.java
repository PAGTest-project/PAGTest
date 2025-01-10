package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.model.BackwardIncompatibleProp;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.DiffResult;

public class ChangedMaxLength_isChangedTest {

  @Mock private DiffContext context;

  @Mock private BackwardIncompatibleProp REQUEST_MAX_LENGTH_DECREASED;

  @Mock private BackwardIncompatibleProp RESPONSE_MAX_LENGTH_INCREASED;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testNoChanges() {
    ChangedMaxLength changedMaxLength = new ChangedMaxLength(10, 10, context);
    assertEquals(DiffResult.NO_CHANGES, changedMaxLength.isChanged());
  }

  @Test
  public void testIncompatibleRequestDecreased() {
    when(context.isRequest()).thenReturn(true);
    when(REQUEST_MAX_LENGTH_DECREASED.enabled(context)).thenReturn(true);
    ChangedMaxLength changedMaxLength = new ChangedMaxLength(10, 5, context);
    assertEquals(DiffResult.INCOMPATIBLE, changedMaxLength.isChanged());
  }

  @Test
  public void testIncompatibleResponseIncreased() {
    when(context.isResponse()).thenReturn(true);
    when(RESPONSE_MAX_LENGTH_INCREASED.enabled(context)).thenReturn(true);
    ChangedMaxLength changedMaxLength = new ChangedMaxLength(5, 10, context);
    assertEquals(DiffResult.INCOMPATIBLE, changedMaxLength.isChanged());
  }

  @Test
  public void testCompatibleRequest() {
    when(context.isRequest()).thenReturn(true);
    when(REQUEST_MAX_LENGTH_DECREASED.enabled(context)).thenReturn(false);
    ChangedMaxLength changedMaxLength = new ChangedMaxLength(10, 5, context);
    assertEquals(DiffResult.COMPATIBLE, changedMaxLength.isChanged());
  }

  @Test
  public void testCompatibleResponse() {
    when(context.isResponse()).thenReturn(true);
    when(RESPONSE_MAX_LENGTH_INCREASED.enabled(context)).thenReturn(false);
    ChangedMaxLength changedMaxLength = new ChangedMaxLength(5, 10, context);
    assertEquals(DiffResult.COMPATIBLE, changedMaxLength.isChanged());
  }
}
