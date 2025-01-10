
package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.openapidiff.core.model.ChangedContent;
import org.openapitools.openapidiff.core.model.ChangedMediaType;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredBuilder;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;

public class ContentDiff_diffTest {

  @Mock
  private OpenApiDiff openApiDiff;

  @Mock
  private SchemaDiff schemaDiff;

  @Mock
  private DeferredBuilder<ChangedContent> builder;

  @Mock
  private DeferredChanged<ChangedContent> deferredChanged;

  @InjectMocks
  private ContentDiff contentDiff;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    when(openApiDiff.getSchemaDiff()).thenReturn(schemaDiff);
    when(builder.build()).thenReturn(deferredChanged);
  }

  @Test
  public void testDiff_WithChangedMediaType() {
    Content left = new Content();
    Content right = new Content();
    DiffContext context = new DiffContext(null);

    MediaType oldMediaType = new MediaType();
    oldMediaType.setSchema(new Schema<>());
    MediaType newMediaType = new MediaType();
    newMediaType.setSchema(new Schema<>());

    left.addMediaType("application/json", oldMediaType);
    right.addMediaType("application/json", newMediaType);

    MapKeyDiff<String, MediaType> mediaTypeDiff = MapKeyDiff.diff(left, right);
    mediaTypeDiff.getSharedKey().add("application/json");

    when(schemaDiff.diff(any(), any(), any())).thenReturn(Optional.of(new ChangedMediaType(oldMediaType.getSchema(), newMediaType.getSchema(), context)));
    when(deferredChanged.mapOptional(any())).thenReturn(deferredChanged);

    DeferredChanged<ChangedContent> result = contentDiff.diff(left, right, context);

    assertTrue(result.isPresent());
  }
}
