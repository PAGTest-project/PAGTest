
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Objects;

public class ChangedParameter_hashCodeTest {

    @Test
    public void testHashCode() {
        // Given
        DiffContext context = new DiffContext(null);
        Parameter oldParameter = new Parameter();
        Parameter newParameter = new Parameter();
        ChangedParameter changedParameter = new ChangedParameter("name", "in", context)
                .setOldParameter(oldParameter)
                .setNewParameter(newParameter);

        // When
        int hashCode = changedParameter.hashCode();

        // Then
        assertEquals(Objects.hash(context, oldParameter, newParameter, "name", "in", false, false, false, false, false, null, null, null, null), hashCode);
    }
}
