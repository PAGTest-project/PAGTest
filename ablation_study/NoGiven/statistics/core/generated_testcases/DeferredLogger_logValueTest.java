
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeferredLogger_logValueTest {

    @Test
    public void testLogValue() {
        Object value = "test";
        Object loggedValue = DeferredLogger.logValue(value);
        assertEquals("test", loggedValue.toString());
    }
}
