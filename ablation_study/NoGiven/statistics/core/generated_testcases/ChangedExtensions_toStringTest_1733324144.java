
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class ChangedExtensions_toStringTest {

    @Test
    public void testToString() {
        Map<String, Object> oldExtensions = new HashMap<>();
        oldExtensions.put("key1", "value1");
        Map<String, Object> newExtensions = new HashMap<>();
        newExtensions.put("key2", "value2");
        DiffContext context = new DiffContext();

        ChangedExtensions changedExtensions = new ChangedExtensions(oldExtensions, newExtensions, context);

        Map<String, Changed> increased = new HashMap<>();
        increased.put("incKey", new Changed());
        changedExtensions.setIncreased(increased);

        Map<String, Changed> missing = new HashMap<>();
        missing.put("missKey", new Changed());
        changedExtensions.setMissing(missing);

        Map<String, Changed> changed = new HashMap<>();
        changed.put("changeKey", new Changed());
        changedExtensions.setChanged(changed);

        String expected = "ChangedExtensions(oldExtensions={key1=value1}, newExtensions={key2=value2}, context=" + context + ", increased={incKey=" + new Changed() + "}, missing={missKey=" + new Changed() + "}, changed={changeKey=" + new Changed() + "})";
        assertEquals(expected, changedExtensions.toString());
    }
}
