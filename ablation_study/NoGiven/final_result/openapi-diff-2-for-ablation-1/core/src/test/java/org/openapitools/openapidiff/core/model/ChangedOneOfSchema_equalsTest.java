package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import io.swagger.v3.oas.models.media.Schema;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedOneOfSchema_equalsTest {

  @Test
  public void testEquals_SameObject() {
    ChangedOneOfSchema schema =
        new ChangedOneOfSchema(new HashMap<>(), new HashMap<>(), new DiffContext(null));
    assertEquals(schema, schema);
  }

  @Test
  public void testEquals_NullObject() {
    ChangedOneOfSchema schema =
        new ChangedOneOfSchema(new HashMap<>(), new HashMap<>(), new DiffContext(null));
    assertNotEquals(schema, null);
  }

  @Test
  public void testEquals_DifferentClass() {
    ChangedOneOfSchema schema =
        new ChangedOneOfSchema(new HashMap<>(), new HashMap<>(), new DiffContext(null));
    assertNotEquals(schema, new Object());
  }

  @Test
  public void testEquals_DifferentFields() {
    Map<String, String> oldMapping1 = new HashMap<>();
    oldMapping1.put("key1", "value1");
    Map<String, String> newMapping1 = new HashMap<>();
    newMapping1.put("key2", "value2");
    DiffContext context1 = new DiffContext(null);
    ChangedOneOfSchema schema1 = new ChangedOneOfSchema(oldMapping1, newMapping1, context1);
    schema1.setIncreased(new HashMap<>()).setMissing(new HashMap<>()).setChanged(new HashMap<>());

    Map<String, String> oldMapping2 = new HashMap<>();
    oldMapping2.put("key1", "value1");
    Map<String, String> newMapping2 = new HashMap<>();
    newMapping2.put("key2", "value2");
    DiffContext context2 = new DiffContext(null);
    ChangedOneOfSchema schema2 = new ChangedOneOfSchema(oldMapping2, newMapping2, context2);
    schema2.setIncreased(new HashMap<>()).setMissing(new HashMap<>()).setChanged(new HashMap<>());

    assertEquals(schema1, schema2);

    schema2.setIncreased(Map.of("key3", new Schema<>()));
    assertNotEquals(schema1, schema2);
  }
}
