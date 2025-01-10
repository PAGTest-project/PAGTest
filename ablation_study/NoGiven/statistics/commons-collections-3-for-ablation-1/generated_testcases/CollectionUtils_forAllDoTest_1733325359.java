
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollectionUtils_forAllDoTest {

    @Test
    void testForAllDo_WithNonNullClosure() {
        Iterable<String> collection = mock(Iterable.class);
        Closure<String> closure = mock(Closure.class);

        Closure<String> result = CollectionUtils.forAllDo(collection, closure);

        assertNotNull(result);
        verify(collection).forEach(closure);
    }

    @Test
    void testForAllDo_WithNullClosure() {
        Iterable<String> collection = mock(Iterable.class);
        Closure<String> closure = null;

        Closure<String> result = CollectionUtils.forAllDo(collection, closure);

        assertNull(result);
        verifyNoInteractions(collection);
    }
}
