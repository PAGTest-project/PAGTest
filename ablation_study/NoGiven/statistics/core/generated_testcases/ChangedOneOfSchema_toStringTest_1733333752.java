
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOneOfSchema_toStringTest {

    @Test
    public void testToString() {
        Map<String, String> oldMapping = new HashMap<>();
        oldMapping.put("key1", "value1");
        Map<String, String> newMapping = new HashMap<>();
        newMapping.put("key2", "value2");
        DiffContext context = new DiffContext();

        Map<String, Schema> increased = new HashMap<>();
        increased.put("incKey", new Schema<>());
        Map<String, Schema> missing = new HashMap<>();
        missing.put("missKey", new Schema<>());
        Map<String, ChangedSchema> changed = new HashMap<>();
        changed.put("changeKey", new ChangedSchema());

        ChangedOneOfSchema changedOneOfSchema = new ChangedOneOfSchema(oldMapping, newMapping, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed);

        String expected = "ChangedOneOfSchema(oldMapping={key1=value1}, newMapping={key2=value2}, context="
                + context
                + ", increased={incKey=class io.swagger.v3.oas.models.media.Schema}, missing={missKey=class io.swagger.v3.oas.models.media.Schema}, changed={changeKey=org.openapitools.openapidiff.core.model.ChangedSchema})";

        assertEquals(expected, changedOneOfSchema.toString());
    }
}
