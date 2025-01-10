
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParametersDiff_containsTest {

    @Test
    public void testContains_ParameterFound() {
        Components components = new Components();
        Parameter param1 = new Parameter().name("param1").in("query");
        Parameter param2 = new Parameter().name("param2").in("query");
        List<Parameter> parameters = Arrays.asList(param1, param2);

        Optional<Parameter> result = ParametersDiff.contains(components, parameters, param1);

        assertTrue(result.isPresent());
    }

    @Test
    public void testContains_ParameterNotFound() {
        Components components = new Components();
        Parameter param1 = new Parameter().name("param1").in("query");
        Parameter param2 = new Parameter().name("param2").in("query");
        List<Parameter> parameters = Arrays.asList(param1);

        Optional<Parameter> result = ParametersDiff.contains(components, parameters, param2);

        assertFalse(result.isPresent());
    }
}
