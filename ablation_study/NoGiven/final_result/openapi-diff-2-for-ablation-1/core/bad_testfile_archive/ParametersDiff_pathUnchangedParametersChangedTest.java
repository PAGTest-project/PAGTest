package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedParameters;
import org.openapitools.openapidiff.core.model.DiffContext;

public class ParametersDiff_pathUnchangedParametersChangedTest {

  @Test
  public void testPathUnchangedParametersChanged_SamePathDifferentParameter() {
    // Given
    Parameter oldParam = new Parameter().name("param1");
    Parameter newParam = new Parameter().name("param2");

    List<Parameter> missingParams = Arrays.asList(oldParam);
    List<Parameter> increasedParams = Arrays.asList(newParam);

    ChangedParameters changedParameters =
        new ChangedParameters(
            Arrays.asList(oldParam), Arrays.asList(newParam), new DiffContext(null));
    changedParameters.setMissing(missingParams);
    changedParameters.setIncreased(increasedParams);

    Map<String, String> parametersMap = new HashMap<>();
    parametersMap.put("param1", "param2");

    DiffContext context = new DiffContext(null);
    context.setParameters(parametersMap);

    // When
    boolean result =
        new ParametersDiff(null).pathUnchangedParametersChanged(changedParameters, context);

    // Then
    assertTrue(result);
  }

  @Test
  public void testPathUnchangedParametersChanged_SamePathSameParameter() {
    // Given
    Parameter oldParam = new Parameter().name("param1");
    Parameter newParam = new Parameter().name("param1");

    List<Parameter> missingParams = Arrays.asList(oldParam);
    List<Parameter> increasedParams = Arrays.asList(newParam);

    ChangedParameters changedParameters =
        new ChangedParameters(
            Arrays.asList(oldParam), Arrays.asList(newParam), new DiffContext(null));
    changedParameters.setMissing(missingParams);
    changedParameters.setIncreased(increasedParams);

    Map<String, String> parametersMap = new HashMap<>();
    parametersMap.put("param1", "param1");

    DiffContext context = new DiffContext(null);
    context.setParameters(parametersMap);

    // When
    boolean result =
        new ParametersDiff(null).pathUnchangedParametersChanged(changedParameters, context);

    // Then
    assertFalse(result);
  }

  @Test
  public void testPathUnchangedParametersChanged_DifferentParameterCount() {
    // Given
    Parameter oldParam = new Parameter().name("param1");
    Parameter newParam = new Parameter().name("param2");

    List<Parameter> missingParams = Arrays.asList(oldParam);
    List<Parameter> increasedParams = Arrays.asList(newParam, new Parameter().name("param3"));

    ChangedParameters changedParameters =
        new ChangedParameters(
            Arrays.asList(oldParam), Arrays.asList(newParam), new DiffContext(null));
    changedParameters.setMissing(missingParams);
    changedParameters.setIncreased(increasedParams);

    Map<String, String> parametersMap = new HashMap<>();
    parametersMap.put("param1", "param2");

    DiffContext context = new DiffContext(null);
    context.setParameters(parametersMap);

    // When
    boolean result =
        new ParametersDiff(null).pathUnchangedParametersChanged(changedParameters, context);

    // Then
    assertFalse(result);
  }

  @Test
  public void testPathUnchangedParametersChanged_NewParameterNameBlank() {
    // Given
    Parameter oldParam = new Parameter().name("param1");
    Parameter newParam = new Parameter().name("param2");

    List<Parameter> missingParams = Arrays.asList(oldParam);
    List<Parameter> increasedParams = Arrays.asList(newParam);

    ChangedParameters changedParameters =
        new ChangedParameters(
            Arrays.asList(oldParam), Arrays.asList(newParam), new DiffContext(null));
    changedParameters.setMissing(missingParams);
    changedParameters.setIncreased(increasedParams);

    Map<String, String> parametersMap = new HashMap<>();
    parametersMap.put("param1", "");

    DiffContext context = new DiffContext(null);
    context.setParameters(parametersMap);

    // When
    boolean result =
        new ParametersDiff(null).pathUnchangedParametersChanged(changedParameters, context);

    // Then
    assertFalse(result);
  }
}
