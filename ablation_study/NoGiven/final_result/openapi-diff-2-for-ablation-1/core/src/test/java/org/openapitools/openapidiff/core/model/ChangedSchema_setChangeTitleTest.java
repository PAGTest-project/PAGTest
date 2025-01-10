package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

public class ChangedSchema_setChangeTitleTest {

  @Test
  public void testSetChangeTitle() {
    ChangedSchema changedSchema = new ChangedSchema();
    changedSchema.setOldSchema(new Schema<>()).setNewSchema(new Schema<>());

    // Given: changeTitle is initially false
    assertFalse(changedSchema.isChangeTitle());

    // When: setChangeTitle is called with true
    changedSchema.setChangeTitle(true);

    // Then: isChangeTitle should return true
    assertTrue(changedSchema.isChangeTitle());
  }
}
