package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;

public class ChangedParameter_equalsTest {

  @Test
  public void testEquals_SameInstance() {
    ChangedParameter param = new ChangedParameter("name", "in", new DiffContext(null));
    assertTrue(param.equals(param));
  }

  @Test
  public void testEquals_NullObject() {
    ChangedParameter param = new ChangedParameter("name", "in", new DiffContext(null));
    assertFalse(param.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    ChangedParameter param = new ChangedParameter("name", "in", new DiffContext(null));
    assertFalse(param.equals("not a ChangedParameter"));
  }

  @Test
  public void testEquals_DifferentParameters() {
    ChangedParameter param1 =
        new ChangedParameter("name", "in", new DiffContext(null))
            .setOldParameter(new Parameter().name("oldParam"))
            .setNewParameter(new Parameter().name("newParam"))
            .setChangeRequired(true)
            .setDeprecated(false)
            .setChangeStyle(true)
            .setChangeExplode(false)
            .setChangeAllowEmptyValue(true);

    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null))
            .setOldParameter(new Parameter().name("oldParam"))
            .setNewParameter(new Parameter().name("newParam"))
            .setChangeRequired(true)
            .setDeprecated(false)
            .setChangeStyle(true)
            .setChangeExplode(false)
            .setChangeAllowEmptyValue(true);

    assertTrue(param1.equals(param2));
  }

  @Test
  public void testEquals_DifferentContext() {
    ChangedParameter param1 = new ChangedParameter("name", "in", new DiffContext(null));
    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null).copyWithMethod(null));
    assertFalse(param1.equals(param2));
  }

  @Test
  public void testEquals_DifferentOldParameter() {
    ChangedParameter param1 =
        new ChangedParameter("name", "in", new DiffContext(null))
            .setOldParameter(new Parameter().name("oldParam1"));
    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null))
            .setOldParameter(new Parameter().name("oldParam2"));
    assertFalse(param1.equals(param2));
  }

  @Test
  public void testEquals_DifferentNewParameter() {
    ChangedParameter param1 =
        new ChangedParameter("name", "in", new DiffContext(null))
            .setNewParameter(new Parameter().name("newParam1"));
    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null))
            .setNewParameter(new Parameter().name("newParam2"));
    assertFalse(param1.equals(param2));
  }

  @Test
  public void testEquals_DifferentChangeRequired() {
    ChangedParameter param1 =
        new ChangedParameter("name", "in", new DiffContext(null)).setChangeRequired(true);
    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null)).setChangeRequired(false);
    assertFalse(param1.equals(param2));
  }

  @Test
  public void testEquals_DifferentDeprecated() {
    ChangedParameter param1 =
        new ChangedParameter("name", "in", new DiffContext(null)).setDeprecated(true);
    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null)).setDeprecated(false);
    assertFalse(param1.equals(param2));
  }

  @Test
  public void testEquals_DifferentChangeStyle() {
    ChangedParameter param1 =
        new ChangedParameter("name", "in", new DiffContext(null)).setChangeStyle(true);
    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null)).setChangeStyle(false);
    assertFalse(param1.equals(param2));
  }

  @Test
  public void testEquals_DifferentChangeExplode() {
    ChangedParameter param1 =
        new ChangedParameter("name", "in", new DiffContext(null)).setChangeExplode(true);
    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null)).setChangeExplode(false);
    assertFalse(param1.equals(param2));
  }

  @Test
  public void testEquals_DifferentChangeAllowEmptyValue() {
    ChangedParameter param1 =
        new ChangedParameter("name", "in", new DiffContext(null)).setChangeAllowEmptyValue(true);
    ChangedParameter param2 =
        new ChangedParameter("name", "in", new DiffContext(null)).setChangeAllowEmptyValue(false);
    assertFalse(param1.equals(param2));
  }
}
