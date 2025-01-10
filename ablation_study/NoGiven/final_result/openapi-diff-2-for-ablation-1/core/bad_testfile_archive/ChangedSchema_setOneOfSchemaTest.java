package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.swagger.v3.oas.models.media.Schema;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.schema.ChangedOneOfSchema;

public class ChangedSchema_setOneOfSchemaTest {

  @Test
  public void testSetOneOfSchema() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();
    ChangedOneOfSchema oneOfSchema = new ChangedOneOfSchema(null, null, null);

    // When
    changedSchema.setContext(new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())));
    changedSchema.setOldSchema(new Schema<>());
    changedSchema.setNewSchema(new Schema<>());
    ChangedSchema result = changedSchema.setOneOfSchema(oneOfSchema);

    // Then
    assertNotNull(result);
    assertEquals(oneOfSchema, changedSchema.getOneOfSchema());
  }
}
