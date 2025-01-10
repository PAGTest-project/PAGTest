
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationException_fillInStackTraceTest {

    @Test
    public void testFillInStackTrace() {
        ValidationException exception = new ValidationException("Test message");
        exception.fillInStackTrace();

        StackTraceElement[] stackTrace = exception.getStackTrace();
        List<StackTraceElement> filteredTrace = List.of(stackTrace);

        boolean containsValidator = filteredTrace.stream()
                .anyMatch(trace -> trace.getClassName().equals(ValidationException.Validator));

        assertEquals(stackTrace.length - 1, filteredTrace.size());
        assertTrue(!containsValidator);
    }
}
