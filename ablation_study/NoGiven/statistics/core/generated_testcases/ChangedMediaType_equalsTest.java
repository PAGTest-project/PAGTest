
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedMediaType_equalsTest {

    @Test
    void testEquals_SameInstance() {
        ChangedMediaType mediaType = new ChangedMediaType(new Schema<>(), new Schema<>(), new DiffContext(null));
        assertTrue(mediaType.equals(mediaType));
    }

    @Test
    void testEquals_NullObject() {
        ChangedMediaType mediaType = new ChangedMediaType(new Schema<>(), new Schema<>(), new DiffContext(null));
        assertFalse(mediaType.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        ChangedMediaType mediaType = new ChangedMediaType(new Schema<>(), new Schema<>(), new DiffContext(null));
        Object differentClassObject = new Object();
        assertFalse(mediaType.equals(differentClassObject));
    }

    @Test
    void testEquals_DifferentFields() {
        Schema oldSchema1 = new Schema<>();
        Schema newSchema1 = new Schema<>();
        DiffContext context1 = new DiffContext(null);
        ChangedMediaType mediaType1 = new ChangedMediaType(oldSchema1, newSchema1, context1);

        Schema oldSchema2 = new Schema<>();
        Schema newSchema2 = new Schema<>();
        DiffContext context2 = new DiffContext(null);
        ChangedMediaType mediaType2 = new ChangedMediaType(oldSchema2, newSchema2, context2);

        assertFalse(mediaType1.equals(mediaType2));
    }

    @Test
    void testEquals_SameFields() {
        Schema oldSchema = new Schema<>();
        Schema newSchema = new Schema<>();
        DiffContext context = new DiffContext(null);
        ChangedMediaType mediaType1 = new ChangedMediaType(oldSchema, newSchema, context);
        ChangedMediaType mediaType2 = new ChangedMediaType(oldSchema, newSchema, context);

        assertTrue(mediaType1.equals(mediaType2));
    }
}
