package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OperationDiff_removePathParameterTest {

  @Test
  public void testRemovePathParameter_ParameterExists() {
    OperationDiff operationDiff = new OperationDiff(null);
    List<Parameter> parameters = new ArrayList<>();
    Parameter param1 = new Parameter();
    param1.setIn("path");
    param1.setName("param1");
    parameters.add(param1);

    operationDiff.removePathParameter("param1", parameters);

    assertEquals(0, parameters.size());
  }

  @Test
  public void testRemovePathParameter_ParameterDoesNotExist() {
    OperationDiff operationDiff = new OperationDiff(null);
    List<Parameter> parameters = new ArrayList<>();
    Parameter param1 = new Parameter();
    param1.setIn("path");
    param1.setName("param1");
    parameters.add(param1);

    operationDiff.removePathParameter("param2", parameters);

    assertEquals(1, parameters.size());
  }
}
