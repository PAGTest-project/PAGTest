package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ChangedParameters_equalsTest {

  @Test
  public void testEquals_SameInstance() {
    ChangedParameters cp =
        new ChangedParameters(
            Collections.emptyList(), Collections.emptyList(), new DiffContext(null));
    assertTrue(cp.equals(cp));
  }

  @Test
  public void testEquals_NullObject() {
    ChangedParameters cp =
        new ChangedParameters(
            Collections.emptyList(), Collections.emptyList(), new DiffContext(null));
    assertFalse(cp.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    ChangedParameters cp =
        new ChangedParameters(
            Collections.emptyList(), Collections.emptyList(), new DiffContext(null));
    assertFalse(cp.equals(new Object()));
  }

  @Test
  public void testEquals_DifferentFields() {
    List<Parameter> oldParams = Arrays.asList(new Parameter(), new Parameter());
    List<Parameter> newParams = Arrays.asList(new Parameter());
    DiffContext context = new DiffContext(null);

    ChangedParameters cp1 = new ChangedParameters(oldParams, newParams, context);
    ChangedParameters cp2 = new ChangedParameters(Collections.emptyList(), newParams, context);

    assertFalse(cp1.equals(cp2));
  }

  @Test
  public void testEquals_SameFields() {
    List<Parameter> oldParams = Arrays.asList(new Parameter(), new Parameter());
    List<Parameter> newParams = Arrays.asList(new Parameter());
    DiffContext context = new DiffContext(null);

    ChangedParameters cp1 = new ChangedParameters(oldParams, newParams, context);
    ChangedParameters cp2 = new ChangedParameters(oldParams, newParams, context);

    assertTrue(cp1.equals(cp2));
  }
}
