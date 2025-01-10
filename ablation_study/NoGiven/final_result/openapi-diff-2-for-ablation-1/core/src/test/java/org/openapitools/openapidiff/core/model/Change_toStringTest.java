package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Change_toStringTest {

  @Test
  public void testToString() {
    Change<String> change = Change.changed("old", "new");
    assertEquals("Change(oldValue=old, newValue=new, type=CHANGED)", change.toString());
  }
}
