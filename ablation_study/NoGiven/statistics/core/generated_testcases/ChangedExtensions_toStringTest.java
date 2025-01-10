
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedExtensions_toStringTest {

    @Test
    public void testToString() {
        Map<String, Object> oldExtensions = new HashMap<>();
        oldExtensions.put("key1", "value1");
        Map<String, Object> newExtensions = new HashMap<>();
        newExtensions.put("key2", "value2");
        DiffContext context = new DiffContext(new OpenApiDiffOptions());

        ChangedExtensions changedExtensions = new ChangedExtensions(oldExtensions, newExtensions, context);

        Map<String, Changed> increased = new HashMap<>();
        increased.put("incKey", new ChangedImpl());
        changedExtensions.setIncreased(increased);

        Map<String, Changed> missing = new HashMap<>();
        missing.put("missKey", new ChangedImpl());
        changedExtensions.setMissing(missing);

        Map<String, Changed> changed = new HashMap<>();
        changed.put("changeKey", new ChangedImpl());
        changedExtensions.setChanged(changed);

        String expected = "ChangedExtensions(oldExtensions={key1=value1}, newExtensions={key2=value2}, context=" + context + ", increased={incKey=" + new ChangedImpl() + "}, missing={missKey=" + new ChangedImpl() + "}, changed={changeKey=" + new ChangedImpl() + "})";
        assertEquals(expected, changedExtensions.toString());
    }

    private static class ChangedImpl implements Changed {
        @Override
        public DiffResult isChanged() {
            return DiffResult.NO_CHANGES;
        }
    }
}
