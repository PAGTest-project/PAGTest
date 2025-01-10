package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.parameters.RequestBody;
import org.junit.jupiter.api.Test;

public class ChangedRequestBody_equalsTest {

  @Test
  public void testEquals_SameInstance() {
    ChangedRequestBody body = new ChangedRequestBody(null, null, null);
    assertTrue(body.equals(body));
  }

  @Test
  public void testEquals_NullObject() {
    ChangedRequestBody body = new ChangedRequestBody(null, null, null);
    assertFalse(body.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    ChangedRequestBody body = new ChangedRequestBody(null, null, null);
    assertFalse(body.equals(new Object()));
  }

  @Test
  public void testEquals_DifferentFields() {
    RequestBody oldBody = new RequestBody();
    RequestBody newBody = new RequestBody();
    DiffContext context = new DiffContext(null);
    ChangedMetadata description = new ChangedMetadata();
    ChangedContent content = new ChangedContent(null, null, null);
    ChangedExtensions extensions = new ChangedExtensions(null, null, null);

    ChangedRequestBody body1 =
        new ChangedRequestBody(oldBody, newBody, context)
            .setChangeRequired(true)
            .setDescription(description)
            .setContent(content)
            .setExtensions(extensions);

    ChangedRequestBody body2 =
        new ChangedRequestBody(oldBody, newBody, context)
            .setChangeRequired(true)
            .setDescription(description)
            .setContent(content)
            .setExtensions(extensions);

    assertTrue(body1.equals(body2));
  }

  @Test
  public void testEquals_DifferentOldRequestBody() {
    RequestBody oldBody1 = new RequestBody();
    RequestBody oldBody2 = new RequestBody();
    RequestBody newBody = new RequestBody();
    DiffContext context = new DiffContext(null);

    ChangedRequestBody body1 = new ChangedRequestBody(oldBody1, newBody, context);
    ChangedRequestBody body2 = new ChangedRequestBody(oldBody2, newBody, context);

    assertFalse(body1.equals(body2));
  }

  @Test
  public void testEquals_DifferentNewRequestBody() {
    RequestBody oldBody = new RequestBody();
    RequestBody newBody1 = new RequestBody();
    RequestBody newBody2 = new RequestBody();
    DiffContext context = new DiffContext(null);

    ChangedRequestBody body1 = new ChangedRequestBody(oldBody, newBody1, context);
    ChangedRequestBody body2 = new ChangedRequestBody(oldBody, newBody2, context);

    assertFalse(body1.equals(body2));
  }

  @Test
  public void testEquals_DifferentContext() {
    RequestBody oldBody = new RequestBody();
    RequestBody newBody = new RequestBody();
    DiffContext context1 = new DiffContext(null);
    DiffContext context2 = new DiffContext(null);

    ChangedRequestBody body1 = new ChangedRequestBody(oldBody, newBody, context1);
    ChangedRequestBody body2 = new ChangedRequestBody(oldBody, newBody, context2);

    assertFalse(body1.equals(body2));
  }

  @Test
  public void testEquals_DifferentChangeRequired() {
    RequestBody oldBody = new RequestBody();
    RequestBody newBody = new RequestBody();
    DiffContext context = new DiffContext(null);

    ChangedRequestBody body1 =
        new ChangedRequestBody(oldBody, newBody, context).setChangeRequired(true);
    ChangedRequestBody body2 =
        new ChangedRequestBody(oldBody, newBody, context).setChangeRequired(false);

    assertFalse(body1.equals(body2));
  }

  @Test
  public void testEquals_DifferentDescription() {
    RequestBody oldBody = new RequestBody();
    RequestBody newBody = new RequestBody();
    DiffContext context = new DiffContext(null);
    ChangedMetadata description1 = new ChangedMetadata();
    ChangedMetadata description2 = new ChangedMetadata();

    ChangedRequestBody body1 =
        new ChangedRequestBody(oldBody, newBody, context).setDescription(description1);
    ChangedRequestBody body2 =
        new ChangedRequestBody(oldBody, newBody, context).setDescription(description2);

    assertFalse(body1.equals(body2));
  }

  @Test
  public void testEquals_DifferentContent() {
    RequestBody oldBody = new RequestBody();
    RequestBody newBody = new RequestBody();
    DiffContext context = new DiffContext(null);
    ChangedContent content1 = new ChangedContent(null, null, null);
    ChangedContent content2 = new ChangedContent(null, null, null);

    ChangedRequestBody body1 =
        new ChangedRequestBody(oldBody, newBody, context).setContent(content1);
    ChangedRequestBody body2 =
        new ChangedRequestBody(oldBody, newBody, context).setContent(content2);

    assertFalse(body1.equals(body2));
  }

  @Test
  public void testEquals_DifferentExtensions() {
    RequestBody oldBody = new RequestBody();
    RequestBody newBody = new RequestBody();
    DiffContext context = new DiffContext(null);
    ChangedExtensions extensions1 = new ChangedExtensions(null, null, null);
    ChangedExtensions extensions2 = new ChangedExtensions(null, null, null);

    ChangedRequestBody body1 =
        new ChangedRequestBody(oldBody, newBody, context).setExtensions(extensions1);
    ChangedRequestBody body2 =
        new ChangedRequestBody(oldBody, newBody, context).setExtensions(extensions2);

    assertFalse(body1.equals(body2));
  }
}
