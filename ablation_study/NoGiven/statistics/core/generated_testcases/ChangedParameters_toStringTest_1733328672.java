
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedParameters_toStringTest {

    @Test
    public void testToString() {
        // Given
        List<Parameter> oldParameterList = Collections.singletonList(new Parameter());
        List<Parameter> newParameterList = Collections.singletonList(new Parameter());
        DiffContext context = new DiffContext();
        ChangedParameters changedParameters = new ChangedParameters(oldParameterList, newParameterList, context);
        List<Parameter> increased = Arrays.asList(new Parameter(), new Parameter());
        List<Parameter> missing = Collections.singletonList(new Parameter());
        List<ChangedParameter> changed = Collections.singletonList(new ChangedParameter());

        // When
        changedParameters.setIncreased(increased);
        changedParameters.setMissing(missing);
        changedParameters.setChanged(changed);

        // Then
        String expected = "ChangedParameters(oldParameterList=[" + oldParameterList.get(0) + "], newParameterList=[" + newParameterList.get(0) + "], context=" + context + ", increased=[" + increased.get(0) + ", " + increased.get(1) + "], missing=[" + missing.get(0) + "], changed=[" + changed.get(0) + "])";
        assertEquals(expected, changedParameters.toString());
    }
}
