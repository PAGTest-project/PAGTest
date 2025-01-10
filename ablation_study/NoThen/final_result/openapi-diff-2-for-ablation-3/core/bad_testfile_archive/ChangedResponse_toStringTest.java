package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangedResponse_toStringTest {

  private ChangedResponse changedResponse;
  private ApiResponse oldApiResponse;
  private ApiResponse newApiResponse;
  private DiffContext context;
  private ChangedMetadata description;
  private ChangedHeaders headers;
  private ChangedContent content;
  private ChangedExtensions extensions;

  @BeforeEach
  public void setUp() {
    oldApiResponse = new ApiResponse().description("Old Response");
    newApiResponse = new ApiResponse().description("New Response");
    context = new DiffContext(null); // Provide a valid OpenApiDiffOptions if needed
    description = new ChangedMetadata();

    Map<String, io.swagger.v3.oas.models.headers.Header> oldHeadersMap = new HashMap<>();
    Map<String, io.swagger.v3.oas.models.headers.Header> newHeadersMap = new HashMap<>();
    headers = new ChangedHeaders(oldHeadersMap, newHeadersMap, context);

    io.swagger.v3.oas.models.media.Content oldContent =
        new io.swagger.v3.oas.models.media.Content();
    io.swagger.v3.oas.models.media.Content newContent =
        new io.swagger.v3.oas.models.media.Content();
    content = new ChangedContent(oldContent, newContent, context);

    Map<String, Object> oldExtensionsMap = new HashMap<>();
    Map<String, Object> newExtensionsMap = new HashMap<>();
    extensions = new ChangedExtensions(oldExtensionsMap, newExtensionsMap, context);

    changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
    changedResponse
        .setDescription(description)
        .setHeaders(headers)
        .setContent(content)
        .setExtensions(extensions);
  }

  @Test
  public void testToString() {
    String expected =
        "ChangedResponse(oldApiResponse=class ApiResponse {\n"
            + "description: Old Response\n"
            + "headers: null\n"
            + "content: null\n"
            + "links: null\n"
            + "extensions: null\n"
            + "$ref: null\n"
            + "}, newApiResponse=class ApiResponse {\n"
            + "description: New Response\n"
            + "headers: null\n"
            + "content: null\n"
            + "links: null\n"
            + "extensions: null\n"
            + "$ref: null\n"
            + "}, context=org.openapitools.openapidiff.core.model.DiffContext@, description=ChangedMetadata(left=null, right=null), headers=ChangedHeaders(oldHeaders={}, newHeaders={}, context=org.openapitools.openapidiff.core.model.DiffContext@, increased=null, missing=null, changed=null), content=ChangedContent(oldContent=class Content {\n"
            + "{}\n"
            + "}, newContent=class Content {\n"
            + "{}\n"
            + "}, context=org.openapitools.openapidiff.core.model.DiffContext@, increased={}, missing={}, changed={}), extensions=ChangedExtensions(oldExtensions={}, newExtensions={}, context=org.openapitools.openapidiff.core.model.DiffContext@, increased={}, missing={}, changed={}))";
    String actual = changedResponse.toString();
    assertEquals(expected, actual);
  }
}
