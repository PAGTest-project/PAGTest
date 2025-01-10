
package org.apache.commons.collections4.sequence;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequencesComparator_getScriptTest {

    @Test
    public void testGetScript() {
        List<String> sequence1 = Arrays.asList("A", "B", "C", "D");
        List<String> sequence2 = Arrays.asList("A", "C", "E");

        SequencesComparator<String> comparator = new SequencesComparator<>(sequence1, sequence2);
        EditScript<String> script = comparator.getScript();

        assertEquals(3, script.getModifications());
        assertEquals(2, script.getLCSLength());
    }
}
