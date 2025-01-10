
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ChangedContent_equalsTest {

    @Test
    void testEquals_SameInstance() {
        Content oldContent = new Content();
        Content newContent = new Content();
        DiffContext context = new DiffContext(null);
        ChangedContent content = new ChangedContent(oldContent, newContent, context);

        assertTrue(content.equals(content));
    }

    @Test
    void testEquals_DifferentClass() {
        Content oldContent = new Content();
        Content newContent = new Content();
        DiffContext context = new DiffContext(null);
        ChangedContent content = new ChangedContent(oldContent, newContent, context);

        assertFalse(content.equals(new Object()));
    }

    @Test
    void testEquals_Null() {
        Content oldContent = new Content();
        Content newContent = new Content();
        DiffContext context = new DiffContext(null);
        ChangedContent content = new ChangedContent(oldContent, newContent, context);

        assertFalse(content.equals(null));
    }

    @Test
    void testEquals_DifferentFields() {
        Content oldContent = new Content();
        Content newContent = new Content();
        DiffContext context = new DiffContext(null);
        ChangedContent content1 = new ChangedContent(oldContent, newContent, context);
        ChangedContent content2 = new ChangedContent(new Content(), new Content(), new DiffContext(null));

        assertFalse(content1.equals(content2));
    }

    @Test
    void testEquals_SameFields() {
        Content oldContent = new Content();
        Content newContent = new Content();
        DiffContext context = new DiffContext(null);
        Map<String, MediaType> increased = new LinkedHashMap<>();
        Map<String, MediaType> missing = new LinkedHashMap<>();
        Map<String, ChangedMediaType> changed = new LinkedHashMap<>();

        ChangedContent content1 = new ChangedContent(oldContent, newContent, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed);

        ChangedContent content2 = new ChangedContent(oldContent, newContent, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed);

        assertTrue(content1.equals(content2));
    }
}
