package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangedPath_equalsTest {

  private ChangedPath changedPath1;
  private ChangedPath changedPath2;
  private DiffContext context;

  @BeforeEach
  public void setUp() {
    context = new DiffContext(null);
    changedPath1 = new ChangedPath("path1", new PathItem(), new PathItem(), context);
    changedPath2 = new ChangedPath("path1", new PathItem(), new PathItem(), context);
  }

  @Test
  public void testEquals_SameObject() {
    assertTrue(changedPath1.equals(changedPath1));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(changedPath1.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    assertFalse(changedPath1.equals("Not a ChangedPath object"));
  }

  @Test
  public void testEquals_DifferentPathUrl() {
    changedPath2 = new ChangedPath("path2", new PathItem(), new PathItem(), context);
    assertFalse(changedPath1.equals(changedPath2));
  }

  @Test
  public void testEquals_DifferentOldPath() {
    changedPath2 =
        new ChangedPath("path1", new PathItem().description("old"), new PathItem(), context);
    assertFalse(changedPath1.equals(changedPath2));
  }

  @Test
  public void testEquals_DifferentNewPath() {
    changedPath2 =
        new ChangedPath("path1", new PathItem(), new PathItem().description("new"), context);
    assertFalse(changedPath1.equals(changedPath2));
  }

  @Test
  public void testEquals_DifferentContext() {
    DiffContext differentContext = new DiffContext(null).setUrl("differentUrl");
    changedPath2 = new ChangedPath("path1", new PathItem(), new PathItem(), differentContext);
    assertFalse(changedPath1.equals(changedPath2));
  }

  @Test
  public void testEquals_DifferentIncreased() {
    Map<PathItem.HttpMethod, Operation> increased = new HashMap<>();
    increased.put(PathItem.HttpMethod.GET, new Operation());
    changedPath2.setIncreased(increased);
    assertFalse(changedPath1.equals(changedPath2));
  }

  @Test
  public void testEquals_DifferentMissing() {
    Map<PathItem.HttpMethod, Operation> missing = new HashMap<>();
    missing.put(PathItem.HttpMethod.POST, new Operation());
    changedPath2.setMissing(missing);
    assertFalse(changedPath1.equals(changedPath2));
  }

  @Test
  public void testEquals_DifferentChanged() {
    changedPath2.setChanged(
        List.of(
            new ChangedOperation(
                "path1", PathItem.HttpMethod.GET, new Operation(), new Operation())));
    assertFalse(changedPath1.equals(changedPath2));
  }

  @Test
  public void testEquals_DifferentExtensions() {
    changedPath2.setExtensions(new ChangedExtensions(null, null, context));
    assertFalse(changedPath1.equals(changedPath2));
  }

  @Test
  public void testEquals_AllFieldsEqual() {
    assertTrue(changedPath1.equals(changedPath2));
  }
}
