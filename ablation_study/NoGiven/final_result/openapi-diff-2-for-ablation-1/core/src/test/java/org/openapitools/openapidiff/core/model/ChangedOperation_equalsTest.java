package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;

class ChangedOperation_equalsTest {

  @Test
  void testEquals_SameInstance() {
    ChangedOperation op =
        new ChangedOperation("/path", PathItem.HttpMethod.GET, new Operation(), new Operation());
    assertTrue(op.equals(op));
  }

  @Test
  void testEquals_NullObject() {
    ChangedOperation op =
        new ChangedOperation("/path", PathItem.HttpMethod.GET, new Operation(), new Operation());
    assertFalse(op.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    ChangedOperation op =
        new ChangedOperation("/path", PathItem.HttpMethod.GET, new Operation(), new Operation());
    Object obj = new Object();
    assertFalse(op.equals(obj));
  }

  @Test
  void testEquals_DifferentFields() {
    Operation oldOp = new Operation();
    Operation newOp = new Operation();
    ChangedOperation op1 = new ChangedOperation("/path", PathItem.HttpMethod.GET, oldOp, newOp);
    ChangedOperation op2 = new ChangedOperation("/path", PathItem.HttpMethod.POST, oldOp, newOp);
    assertFalse(op1.equals(op2));
  }

  @Test
  void testEquals_SameFields() {
    Operation oldOp = new Operation();
    Operation newOp = new Operation();
    ChangedOperation op1 = new ChangedOperation("/path", PathItem.HttpMethod.GET, oldOp, newOp);
    ChangedOperation op2 = new ChangedOperation("/path", PathItem.HttpMethod.GET, oldOp, newOp);
    assertTrue(op1.equals(op2));
  }
}
