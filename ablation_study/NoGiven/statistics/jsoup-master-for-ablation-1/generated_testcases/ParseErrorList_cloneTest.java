
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParseErrorList_cloneTest {

    @Test
    void testClone() throws CloneNotSupportedException {
        ParseErrorList original = ParseErrorList.tracking(10);
        original.add(new ParseError("Error 1"));

        ParseErrorList cloned = (ParseErrorList) original.clone();

        assertTrue(cloned.canAddError());
        assertEquals(original.getMaxSize(), cloned.getMaxSize());
        assertEquals(original.get(0).getErrorMessage(), cloned.get(0).getErrorMessage());
    }
}
