package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Collections;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class ChangedSecurityScheme_toStringTest {

  @Test
  public void testToString() {
    SecurityScheme oldScheme = new SecurityScheme();
    oldScheme.setType(SecurityScheme.Type.APIKEY);
    oldScheme.setName("api_key");
    oldScheme.setIn(SecurityScheme.In.HEADER);

    SecurityScheme newScheme = new SecurityScheme();
    newScheme.setType(SecurityScheme.Type.HTTP);
    newScheme.setScheme("bearer");
    newScheme.setBearerFormat("JWT");

    ChangedSecuritySchemeScopes changedScopes =
        new ChangedSecuritySchemeScopes(Collections.emptyList(), Collections.emptyList());
    ChangedMetadata description = new ChangedMetadata();
    ChangedOAuthFlows oAuthFlows = new ChangedOAuthFlows(null, null);
    ChangedExtensions extensions = new ChangedExtensions(new HashMap<>(), new HashMap<>(), null);

    ChangedSecurityScheme changedSecurityScheme =
        new ChangedSecurityScheme(oldScheme, newScheme, null)
            .setChangedType(true)
            .setChangedIn(false)
            .setChangedScheme(true)
            .setChangedBearerFormat(true)
            .setChangedOpenIdConnectUrl(false)
            .setChangedScopes(changedScopes)
            .setDescription(description)
            .setOAuthFlows(oAuthFlows)
            .setExtensions(extensions);

    String expected =
        "ChangedSecurityScheme(oldSecurityScheme=SecurityScheme{type=apiKey, description='null', name='api_key', in=header, scheme='null', bearerFormat='null', flows=null, openIdConnectUrl='null'}, newSecurityScheme=SecurityScheme{type=http, description='null', name='null', in=null, scheme=bearer, bearerFormat=JWT, flows=null, openIdConnectUrl='null'}, changedType=true, changedIn=false, changedScheme=true, changedBearerFormat=true, changedOpenIdConnectUrl=false, changedScopes=ChangedSecuritySchemeScopes{increased=[], missing=[]}, description=ChangedMetadata{diffResult=NO_CHANGES, oldValue='null', newValue='null'}, oAuthFlows=ChangedOAuthFlows{diffResult=NO_CHANGES, added=null, decreased=null, changed=null}, extensions=ChangedExtensions{diffResult=NO_CHANGES, changed=[]})";

    assertEquals(expected, changedSecurityScheme.toString());
  }
}
