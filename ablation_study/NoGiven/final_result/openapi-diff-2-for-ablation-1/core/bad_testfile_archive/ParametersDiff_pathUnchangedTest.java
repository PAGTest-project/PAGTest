package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedParameter;
import org.openapitools.openapidiff.core.model.ChangedParameters;
import org.openapitools.openapidiff.core.model.DiffContext;

public class ParametersDiff_pathUnchangedTest {

  @Test
  public void testPathUnchanged_PathParamsMatchAndNoChanges() {
    ParametersDiff parametersDiff = new ParametersDiff(null);
    DiffContext context = new DiffContext(null);
    context.setLeftAndRightUrls("/api/v1/users/{id}", "/api/v1/users/{id}");

    ChangedParameters changedParameters =
        new ChangedParameters(Collections.emptyList(), Collections.emptyList(), context);

    assertTrue(parametersDiff.pathUnchanged(changedParameters, context));
  }

  @Test
  public void testPathUnchanged_PathParamsCountMismatch() {
    ParametersDiff parametersDiff = new ParametersDiff(null);
    DiffContext context = new DiffContext(null);
    context.setLeftAndRightUrls("/api/v1/users/{id}", "/api/v1/users/{id}/{name}");

    ChangedParameters changedParameters =
        new ChangedParameters(Collections.emptyList(), Collections.emptyList(), context);

    assertFalse(parametersDiff.pathUnchanged(changedParameters, context));
  }

  @Test
  public void testPathUnchanged_ParamLessPathMismatch() {
    ParametersDiff parametersDiff = new ParametersDiff(null);
    DiffContext context = new DiffContext(null);
    context.setLeftAndRightUrls("/api/v1/users/{id}", "/api/v1/items/{id}");

    ChangedParameters changedParameters =
        new ChangedParameters(Collections.emptyList(), Collections.emptyList(), context);

    assertFalse(parametersDiff.pathUnchanged(changedParameters, context));
  }

  @Test
  public void testPathUnchanged_ChangedParametersNotEmpty() {
    ParametersDiff parametersDiff = new ParametersDiff(null);
    DiffContext context = new DiffContext(null);
    context.setLeftAndRightUrls("/api/v1/users/{id}", "/api/v1/users/{id}");

    List<Parameter> oldParams = new ArrayList<>();
    List<Parameter> newParams = new ArrayList<>();
    Parameter param = new Parameter();
    param.setName("id");
    oldParams.add(param);
    newParams.add(param);

    ChangedParameters changedParameters = new ChangedParameters(oldParams, newParams, context);
    changedParameters.getChanged().add(new ChangedParameter(param, param, context));

    assertFalse(parametersDiff.pathUnchanged(changedParameters, context));
  }
}
