
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.media.Schema;

public class ChangedSchema_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        ChangedSchema schema = new ChangedSchema();
        assertTrue(schema.equals(schema));
    }

    @Test
    public void testEquals_NullObject() {
        ChangedSchema schema = new ChangedSchema();
        assertFalse(schema.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        ChangedSchema schema = new ChangedSchema();
        Object differentClassObject = new Object();
        assertFalse(schema.equals(differentClassObject));
    }

    @Test
    public void testEquals_DifferentSchemas() {
        ChangedSchema schema1 = new ChangedSchema();
        schema1.setOldSchema(new Schema<>().description("old"));
        schema1.setNewSchema(new Schema<>().description("new"));

        ChangedSchema schema2 = new ChangedSchema();
        schema2.setOldSchema(new Schema<>().description("old"));
        schema2.setNewSchema(new Schema<>().description("new"));

        assertTrue(schema1.equals(schema2));
    }

    @Test
    public void testEquals_DifferentSchemas_NotEqual() {
        ChangedSchema schema1 = new ChangedSchema();
        schema1.setOldSchema(new Schema<>().description("old1"));
        schema1.setNewSchema(new Schema<>().description("new1"));

        ChangedSchema schema2 = new ChangedSchema();
        schema2.setOldSchema(new Schema<>().description("old2"));
        schema2.setNewSchema(new Schema<>().description("new2"));

        assertFalse(schema1.equals(schema2));
    }
}
