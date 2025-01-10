package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.security.OAuthFlows;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.model.ChangedOAuthFlows;
import org.openapitools.openapidiff.core.model.DiffContext;

public class OAuthFlowsDiff_diffTest {

  private OAuthFlowsDiff oAuthFlowsDiff;
  private OpenApiDiff openApiDiff;
  private DiffContext context;

  @BeforeEach
  public void setUp() {
    openApiDiff = mock(OpenApiDiff.class);
    oAuthFlowsDiff = new OAuthFlowsDiff(openApiDiff);
    context = mock(DiffContext.class);
  }

  @Test
  public void testDiffWithNonNullOAuthFlows() {
    OAuthFlows left = new OAuthFlows();
    OAuthFlows right = new OAuthFlows();

    when(openApiDiff.getOAuthFlowDiff().diff(left.getImplicit(), right.getImplicit(), context))
        .thenReturn(Optional.empty());
    when(openApiDiff.getOAuthFlowDiff().diff(left.getPassword(), right.getPassword(), context))
        .thenReturn(Optional.empty());
    when(openApiDiff
            .getOAuthFlowDiff()
            .diff(left.getClientCredentials(), right.getClientCredentials(), context))
        .thenReturn(Optional.empty());
    when(openApiDiff
            .getOAuthFlowDiff()
            .diff(left.getAuthorizationCode(), right.getAuthorizationCode(), context))
        .thenReturn(Optional.empty());
    when(openApiDiff.getExtensionsDiff().diff(Mockito.any(), Mockito.any()))
        .thenReturn(Optional.empty());

    Optional<ChangedOAuthFlows> result = oAuthFlowsDiff.diff(left, right, context);

    assertTrue(result.isPresent());
  }

  @Test
  public void testDiffWithNullOAuthFlows() {
    OAuthFlows left = null;
    OAuthFlows right = null;

    when(openApiDiff.getExtensionsDiff().diff(Mockito.any(), Mockito.any()))
        .thenReturn(Optional.empty());

    Optional<ChangedOAuthFlows> result = oAuthFlowsDiff.diff(left, right, context);

    assertTrue(result.isPresent());
  }
}
