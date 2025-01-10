package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.media.Schema;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedOneOfSchema_toStringTest {

  @Test
  public void testToString() {
    Map<String, String> oldMapping = new HashMap<>();
    oldMapping.put("key1", "value1");
    Map<String, String> newMapping = new HashMap<>();
    newMapping.put("key2", "value2");
    DiffContext context = new DiffContext(null); // Provide the required parameter

    Map<String, Schema> increased = new HashMap<>();
    increased.put("incKey", new Schema<>());
    Map<String, Schema> missing = new HashMap<>();
    missing.put("missKey", new Schema<>());
    Map<String, ChangedSchema> changed = new HashMap<>();
    changed.put("changeKey", new ChangedSchema());

    ChangedOneOfSchema changedOneOfSchema =
        new ChangedOneOfSchema(oldMapping, newMapping, context)
            .setIncreased(increased)
            .setMissing(missing)
            .setChanged(changed);

    String expected =
        "ChangedOneOfSchema(oldMapping={key1=value1}, newMapping={key2=value2}, context="
            + context
            + ", increased={incKey=class io.swagger.v3.oas.models.media.Schema}, missing={missKey=class io.swagger.v3.oas.models.media.Schema}, changed={changeKey=ChangedSchema(context=null, oldSchema=null, newSchema=null, type=null, changedProperties={}, increasedProperties={}, missingProperties={}, changeDeprecated=false, description=null, changeTitle=false, required=null, changeDefault=false, enumeration=null, changeFormat=false, readOnly=null, writeOnly=null, changedType=false, maxLength=null, numericRange=null, discriminatorPropertyChanged=false, items=null, oneOfSchema=null, addProp=null, extensions=null)})";

    assertEquals(expected, changedOneOfSchema.toString());
  }
}
