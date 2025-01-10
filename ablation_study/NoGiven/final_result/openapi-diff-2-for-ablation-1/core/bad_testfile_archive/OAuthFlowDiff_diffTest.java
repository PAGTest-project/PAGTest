package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.security.OAuthFlow;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.openapidiff.core.model.ChangedOAuthFlow;
import org.openapitools.openapidiff.core.model.DiffContext;

@ExtendWith(MockitoExtension.class)
public class OAuthFlowDiff_diffTest {

  @Mock private OpenApiDiff openApiDiff;

  @Mock private ExtensionsDiff extensionsDiff;

  private OAuthFlowDiff oAuthFlowDiff;

  @BeforeEach
  public void setUp() {
    oAuthFlowDiff = new OAuthFlowDiff(openApiDiff);
  }

  @Test
  public void testDiff_BothNonNull() {
    OAuthFlow left = new OAuthFlow();
    OAuthFlow right = new OAuthFlow();
    DiffContext context = new DiffContext(null);

    left.setAuthorizationUrl("http://left.com");
    right.setAuthorizationUrl("http://right.com");

    when(openApiDiff.getExtensionsDiff()).thenReturn(extensionsDiff);
    when(extensionsDiff.diff(OAuthFlowDiff.getExtensions(left), OAuthFlowDiff.getExtensions(right)))
        .thenReturn(Optional.empty());

    Optional<ChangedOAuthFlow> result = oAuthFlowDiff.diff(left, right, context);

    assertTrue(result.isPresent());
    assertTrue(result.get().isAuthorizationUrl());
  }

  @Test
  public void testDiff_OneNull() {
    OAuthFlow left = new OAuthFlow();
    OAuthFlow right = null;
    DiffContext context = new DiffContext(null);

    when(openApiDiff.getExtensionsDiff()).thenReturn(extensionsDiff);
    when(extensionsDiff.diff(OAuthFlowDiff.getExtensions(left), OAuthFlowDiff.getExtensions(right)))
        .thenReturn(Optional.empty());

    Optional<ChangedOAuthFlow> result = oAuthFlowDiff.diff(left, right, context);

    assertTrue(result.isPresent());
  }
}
